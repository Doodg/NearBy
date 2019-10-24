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

class RemoteDataStore(private val remoteDataStore: NearByRemote) :
    NearByDataStore {
    override fun getVenuePhoto(id: String): Observable<PhotoResponse> {
        return remoteDataStore.getVenuePhoto(id)
    }

    override fun deleteCachedPlaces(): Completable {
        throw UnsupportedOperationException("Just database request ")
    }

    override fun insertPlaces(placesList: List<VenueItemEntity>): Completable {
        throw UnsupportedOperationException("Just database request ")

    }

    override fun getNearbyPlaces(): Flowable<List<VenueItemEntity>> {
        throw UnsupportedOperationException("Just database request ")
    }

    override fun getNearbyPlaces(lnglat: String): Observable<NearPlacesResponse> {
        return remoteDataStore.getNearbyPlaces(lnglat)
    }
}