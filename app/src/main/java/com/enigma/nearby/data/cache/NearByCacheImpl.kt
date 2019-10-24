package com.enigma.nearby.data.cache

import com.enigma.nearby.data.cache.room.NearByDataBase
import com.enigma.nearby.data.repos.NearByCache
import io.reactivex.Completable
import io.reactivex.Flowable

class NearByCacheImpl(private val nearByDataBase: NearByDataBase) : NearByCache {
    override fun deletePlaces(): Completable {
        return nearByDataBase.cachedPlacesItem().deletePlaces()
    }

    override fun insertPlaces(placesList: List<VenueItemEntity>): Completable {
        return nearByDataBase.cachedPlacesItem().insertPlaces(placesList)
    }

    override fun getNearbyPlaces(): Flowable<List<VenueItemEntity>> {
        return nearByDataBase.cachedPlacesItem().getPlaces()
    }
}