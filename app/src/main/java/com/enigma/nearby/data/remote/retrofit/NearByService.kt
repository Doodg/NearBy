package com.enigma.nearby.data.remote.retrofit

import com.enigma.nearby.model.photo.PhotoResponse
import com.enigma.nearby.model.venue.NearPlacesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NearByService {
    @GET("venues/explore")
    fun getNearByPlaces(@Query("ll") lnglat: String): Observable<NearPlacesResponse>

    @GET("venues/{VENUE_ID}/photos")
    fun getVenueImage(@Path("VENUE_ID") id: String): Observable<PhotoResponse>
}