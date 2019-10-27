package com.enigma.nearby.data

import com.enigma.nearby.data.cache.VenueItemEntity
import com.enigma.nearby.model.photo.PhotoResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface UseCaseRepository {
    fun removedCachedPlaces():Completable
    fun getNearByPlaces(lnglat: String): Completable
    fun getNearbyPlaces(): Flowable<List<VenueItemEntity>>
    fun getVenuePhoto(id: String): Observable<String>

}