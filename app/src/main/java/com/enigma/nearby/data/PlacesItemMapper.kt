package com.enigma.nearby.data

import com.enigma.nearby.data.cache.VenueItemEntity
import com.enigma.nearby.model.venue.ItemsItem

class PlacesItemMapper() {
    fun mapToDataBaseObject(itemPlacesRemote: List<ItemsItem>) =
        ArrayList<VenueItemEntity>().apply {
            addAll(itemPlacesRemote.map { item ->
                VenueItemEntity(
                    placeName = item.venue?.name!!,
                    placeAddress = item.venue.location?.formattedAddressCorrectly()!!,
                    placeIcon = item.venue.categories?.get(0)?.icon?.prefix.plus("70*70" + (item.venue.categories?.get(0)?.icon?.suffix))
                )
            })
        }
}
