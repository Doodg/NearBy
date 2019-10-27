package com.enigma.nearby.data

import com.enigma.nearby.data.cache.VenueItemEntity
import com.enigma.nearby.model.venue.ItemsItem
import com.enigma.nearby.model.venue.Venue

class PlacesItemMapper() {
    fun mapToDataBaseObject(itemPlacesRemote: Venue) =
        ArrayList<VenueItemEntity>().apply {
            itemPlacesRemote.formattedPhotoUrl?.let {
                VenueItemEntity(
                    placeName = itemPlacesRemote.name!!,
                    placeAddress = itemPlacesRemote.location?.formattedAddressCorrectly()!!,
                    placeIcon = it
                )
            }?.let {
                add(
                    it
                )
            }
        }
}
