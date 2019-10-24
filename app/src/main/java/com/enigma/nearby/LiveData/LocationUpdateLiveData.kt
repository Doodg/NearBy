package com.enigma.nearby.LiveData

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import com.enigma.nearby.NearByApplication


class LocationUpdateLiveData(private val sharedPreferences: SharedPreferences) :
    LiveData<Location>(), LocationListener {
    var locationManager: LocationManager =
        NearByApplication.getInstance().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    var deviceCurrentLocation: Location? = null


    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0F, this)

    }

    override fun onInactive() {
        super.onInactive()
        locationManager.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location?) {
        if (deviceCurrentLocation == null) {
            if (location != null) {
                this.deviceCurrentLocation = location
                value = deviceCurrentLocation
            }
        } else {
            if (sharedPreferences.getBoolean(IS_REALTIME, false) == true) {
                if (location?.distanceTo(deviceCurrentLocation)!! > 500) {
                    deviceCurrentLocation = location
                    value = deviceCurrentLocation
                }
            }
        }
        Log.e("Changes", "${location?.latitude}    ${location?.longitude}")
        Log.e("Primiry", "${deviceCurrentLocation?.latitude}    ${deviceCurrentLocation?.longitude}"
        )

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
// do no thing
    }

    override fun onProviderEnabled(provider: String?) {
// do no thing
    }

    override fun onProviderDisabled(provider: String?) {
// do no thing
    }
}