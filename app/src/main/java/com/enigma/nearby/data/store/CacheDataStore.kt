package com.enigma.nearby.data.store

import com.enigma.nearby.data.cache.VenueItemEntity
import com.enigma.nearby.data.repos.NearByCache
import com.enigma.nearby.data.repos.NearByDataStore
import com.enigma.nearby.model.photo.PhotoResponse
import com.enigma.nearby.model.venue.NearPlacesResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException

class CacheDataStore(private val nearByCache: NearByCache) : NearByDataStore {
    override fun getVenuePhoto(id: String): Observable<PhotoResponse> {
        throw UnsupportedOperationException("Just remote request ")
    }

    override fun deleteCachedPlaces(): Completable {
        return nearByCache.deletePlaces()
    }

    override fun insertPlaces(placesList: List<VenueItemEntity>): Completable {
        return nearByCache.insertPlaces(placesList)
    }


    override fun getCachedNearbyPlaces(): Flowable<List<VenueItemEntity>> {
        return nearByCache.getCachedNearbyPlaces()
    }

    override fun getRemotlyNearbyPlaces(lnglat: String): Observable<NearPlacesResponse> {
        throw UnsupportedOperationException("Just remote request ")
    }
}