package com.enigma.nearby.data.repos

import com.enigma.nearby.data.cache.VenueItemEntity
import com.enigma.nearby.model.photo.PhotoResponse
import com.enigma.nearby.model.venue.NearPlacesResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface NearByDataStore {
    fun insertPlaces(placesList: List<VenueItemEntity>): Completable
    fun deleteCachedPlaces(): Completable
    fun getCachedNearbyPlaces(): Flowable<List<VenueItemEntity>>
    fun getRemotlyNearbyPlaces(lnglat: String): Observable<NearPlacesResponse>
    fun getVenuePhoto(id: String): Observable<PhotoResponse>

}