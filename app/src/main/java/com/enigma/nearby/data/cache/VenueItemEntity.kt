package com.enigma.nearby.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.enigma.nearby.data.cache.room.DataBaseConstant

@Entity(tableName = DataBaseConstant.PLACES_TABLE)
data class VenueItemEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var placeName: String, var placeAddress: String, var placeIcon: String
)