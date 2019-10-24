package com.enigma.nearby.data.store

import com.enigma.nearby.data.PlacesItemMapper
import com.enigma.nearby.data.UseCaseRepository
import com.enigma.nearby.data.cache.VenueItemEntity
import com.enigma.nearby.model.photo.PhotoResponse
import com.enigma.nearby.model.venue.GroupsItem
import com.enigma.nearby.model.venue.ItemsItem
import com.enigma.nearby.model.venue.Response
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.Function

class NearByDataRepoImpl(
    private val nearByDataStoreFactory: NearByDataStoreFactory,
    private val mapper: PlacesItemMapper
) : UseCaseRepository {
    override fun getVenuePhoto(id: String): Observable<PhotoResponse> {
        return nearByDataStoreFactory.getRemoteDataStore().getVenuePhoto(id)
    }

    override fun removedCachedPlaces(): Completable {
        return nearByDataStoreFactory.getCacheDataStore().deleteCachedPlaces()
    }


    override fun getNearbyPlaces(): Flowable<List<VenueItemEntity>> {
        return nearByDataStoreFactory.getCacheDataStore().getNearbyPlaces()
    }

    override fun getNearByPlaces(lnglat: String): Completable {//Remotly
        removedCachedPlaces()
        return Completable.defer {
            nearByDataStoreFactory.getRemoteDataStore().getNearbyPlaces(lnglat)
                .flatMapCompletable { nearPlacesRemotly ->
                    nearByDataStoreFactory.getCacheDataStore()
                        .insertPlaces(mapper.mapToDataBaseObject(nearPlacesRemotly.response?.groups?.first()?.items as List<ItemsItem>))
                }
        }
    }


}


//    override fun getNearByPlaces(lnglat: String): Completable {//Remotly
//        removedCachedPlaces()
//        return Completable.defer {
//            nearByDataStoreFactory.getRemoteDataStore().getNearbyPlaces(lnglat).flatMap {
//                return@flatMap Observable.fromIterable(it.response?.groups)
//            }.flatMap {
//                return@flatMap Observable.fromIterable(it.items)
//            }.flatMap { venueItem ->
//               val currentVenuePhoto : Observable<String> = getVenuePhoto(venueItem.venue?.id!!).flatMap {
//                   it.response?.photos?.items?.first()?.prefix.plus(it.response?.photos?.items?.first()?.suffix)
//                   venueItem.venue.formattedIcon =currentVenuePhoto
//                   return@flatMap venueItem
//               }
//            }
//                .flatMapCompletable { nearPlacesRemotly ->
//                    nearByDataStoreFactory.getCacheDataStore()
//                        .insertPlaces(mapper.mapToDataBaseObject(nearPlacesRemotly.response?.groups?.first()?.items as List<ItemsItem>))
//                }
//        }
//    }