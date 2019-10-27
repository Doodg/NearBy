package com.enigma.nearby.data

import com.enigma.nearby.data.cache.VenueItemEntity
import com.enigma.nearby.model.photo.PhotoResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface UseCaseRepository {
    fun removedCachedPlaces():Completable
    fun getRemotlyNearByPlaces(lnglat: String): Completable
    fun getCachedNearbyPlaces(): Flowable<List<VenueItemEntity>>
    fun getVenuePhoto(id: String): Observable<String>

}