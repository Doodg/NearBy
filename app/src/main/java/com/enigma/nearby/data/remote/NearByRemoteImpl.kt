package com.enigma.nearby.data.remote

import com.enigma.nearby.data.remote.retrofit.NearByService
import com.enigma.nearby.data.repos.NearByRemote
import com.enigma.nearby.model.photo.PhotoResponse
import com.enigma.nearby.model.venue.NearPlacesResponse
import io.reactivex.Observable

class NearByRemoteImpl(private val service: NearByService) : NearByRemote {
    override fun getVenuePhoto(id: String): Observable<PhotoResponse> {
        return service.getVenueImage(id)
    }

    override fun getNearbyPlaces(lnglat: String): Observable<NearPlacesResponse> {
        return service.getNearByPlaces(lnglat)
    }
}