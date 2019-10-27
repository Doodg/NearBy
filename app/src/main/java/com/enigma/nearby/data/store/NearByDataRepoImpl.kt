package com.enigma.nearby.data.store

import com.enigma.nearby.data.PlacesItemMapper
import com.enigma.nearby.data.UseCaseRepository
import com.enigma.nearby.data.cache.VenueItemEntity
import com.enigma.nearby.model.venue.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class NearByDataRepoImpl(
    private val nearByDataStoreFactory: NearByDataStoreFactory,
    private val mapper: PlacesItemMapper
) : UseCaseRepository {
    override fun getVenuePhoto(id: String): Observable<String> {
        return nearByDataStoreFactory.getRemoteDataStore().getVenuePhoto(id).map { it.response }
            .flatMap { Observable.fromIterable(it.photos?.items) }
            .take(1).map { itemsItem ->
                itemsItem.prefix + "612x612" + itemsItem.suffix

            }
    }

    override fun removedCachedPlaces(): Completable {
        return nearByDataStoreFactory.getCacheDataStore().deleteCachedPlaces()
    }


    override fun getCachedNearbyPlaces(): Flowable<List<VenueItemEntity>> {
        return nearByDataStoreFactory.getCacheDataStore().getCachedNearbyPlaces()
    }

    override fun getRemotlyNearByPlaces(lnglat: String): Completable {//Remotly
        removedCachedPlaces()
        return Completable.defer {
            nearByDataStoreFactory.getRemoteDataStore().getRemotlyNearbyPlaces(lnglat)
                .map { it.response?.groups }.flatMap {
                    Observable.fromIterable(it)
                }.map { it.items }.flatMap { Observable.fromIterable(it) }
                .flatMap {
                    Observable.zip(Observable.just(it.venue),
                        it.venue?.id?.let { it1 -> getVenuePhoto(it1) },
                        BiFunction<Venue?, String?, Venue?> { v, p ->
                            v.formattedPhotoUrl = p
                            v
                        })
                }.flatMapCompletable {
                    nearByDataStoreFactory.getCacheDataStore()
                        .insertPlaces(mapper.mapToDataBaseObject(it))
                }
        }
    }

}
