package com.enigma.nearby.data.repos

import com.enigma.nearby.model.photo.PhotoResponse
import com.enigma.nearby.model.venue.NearPlacesResponse
import io.reactivex.Observable

interface NearByRemote {
    fun getNearbyPlaces(lnglat: String): Observable<NearPlacesResponse>
    fun getVenuePhoto(id: String): Observable<PhotoResponse>
}