package com.enigma.nearby.LiveData

import android.content.Context
import android.net.NetworkInfo
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData


class NetworkStatusLiveData(private var context: Context) : LiveData<NetworkInfo>() {
    lateinit var cm: ConnectivityManager

    override fun onActive() {
        super.onActive()
        cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager;
        value = cm.getActiveNetworkInfo();
    }

    override fun onInactive() {
        super.onInactive()

    }

}