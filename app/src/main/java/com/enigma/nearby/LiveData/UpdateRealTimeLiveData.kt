package com.enigma.nearby.LiveData

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

const val IS_REALTIME = "is_realtime"
class UpdateRealTimeLiveData(private var sharedPreferences: SharedPreferences) :
    LiveData<Boolean>() {

    private val mTokenSharedPreferenceListener =
        SharedPreferences.OnSharedPreferenceChangeListener({ sharedPreferences: SharedPreferences?, key: String? ->
            if (key == IS_REALTIME) {
                value = sharedPreferences?.getBoolean(IS_REALTIME, false)
            }
        })


    override fun onActive() {
        super.onActive()
        value = sharedPreferences.getBoolean(IS_REALTIME, false)
        sharedPreferences.registerOnSharedPreferenceChangeListener(mTokenSharedPreferenceListener)
    }

    override fun onInactive() {
        super.onInactive()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(mTokenSharedPreferenceListener)
    }

}