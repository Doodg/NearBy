package com.enigma.nearby.data.repos

import com.enigma.nearby.data.cache.VenueItemEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface NearByCache {
    fun insertPlaces(placesList: List<VenueItemEntity>): Completable
    fun getCachedNearbyPlaces(): Flowable<List<VenueItemEntity>>
    fun deletePlaces(): Completable
}