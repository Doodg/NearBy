package com.enigma.nearby.data.cache.room

import androidx.room.*
import com.enigma.nearby.data.cache.VenueItemEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface PlacesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaces(placesList: List<VenueItemEntity>): Completable

    @Query("SELECT * FROM ${DataBaseConstant.PLACES_TABLE}")
    fun getPlaces(): Flowable<List<VenueItemEntity>>

    @Query("DELETE FROM ${DataBaseConstant.PLACES_TABLE}")
    fun deletePlaces(): Completable


}