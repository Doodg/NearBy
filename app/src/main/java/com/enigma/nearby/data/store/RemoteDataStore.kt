package com.enigma.nearby.data.store

import com.enigma.nearby.data.cache.VenueItemEntity
import com.enigma.nearby.data.repos.NearByDataStore
import com.enigma.nearby.data.repos.NearByRemote
import com.enigma.nearby.model.photo.PhotoResponse
import com.enigma.nearby.model.venue.NearPlacesResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException

class RemoteDataStore(private val nearByRemote: NearByRemote) :
    NearByDataStore {
    override fun getVenuePhoto(id: String): Observable<PhotoResponse> {
        return nearByRemote.getVenuePhoto(id)
    }

    override fun deleteCachedPlaces(): Completable {
        throw UnsupportedOperationException("Just database request ")
    }

    override fun insertPlaces(placesList: List<VenueItemEntity>): Completable {
        throw UnsupportedOperationException("Just database request ")

    }

    override fun getCachedNearbyPlaces(): Flowable<List<VenueItemEntity>> {
        throw UnsupportedOperationException("Just database request ")
    }

    override fun getRemotlyNearbyPlaces(lnglat: String): Observable<NearPlacesResponse> {
        return nearByRemote.getRemoteNearbyPlaces(lnglat)
    }
}