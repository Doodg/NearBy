package com.enigma.nearby.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigma.nearby.enums.ResponseType
import com.enigma.nearby.LiveData.LocationUpdateLiveData
import com.enigma.nearby.LiveData.UpdateRealTimeLiveData
import com.enigma.nearby.data.UseCaseRepository
import com.enigma.nearby.data.cache.VenueItemEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

const val IS_REALTIME = "is_realtime"

class MainActivityViewModel(
    private val useCaseRepository: UseCaseRepository,
    private val locationLiveData: LocationUpdateLiveData,
    private var sharedPreferences: SharedPreferences,
    private var updateRealTimeLiveData: UpdateRealTimeLiveData
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    val placesMutableLiveData: MutableLiveData<List<VenueItemEntity?>> = MutableLiveData()
    val responseTypeMutableLiveData: MutableLiveData<ResponseType> = MutableLiveData()
    fun executeNearByPlace(lnglat: String) {
        disposables.add(
            useCaseRepository.getNearByPlaces(lnglat).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(NearPlacesObserver())
        )
    }

    fun retrieveDataFromCached() {
        disposables.add(
            useCaseRepository.getNearbyPlaces().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                    CachedNearPlacesObserver()
                )
        )

    }

    private inner class NearPlacesObserver : DisposableCompletableObserver() {

        override fun onComplete() {
            responseTypeMutableLiveData.postValue(ResponseType.SUCCESS)
        }

        override fun onError(e: Throwable) {
            errorMapper(e)

        }

    }

    private inner class CachedNearPlacesObserver : DisposableSubscriber<List<VenueItemEntity>>() {
        override fun onNext(t: List<VenueItemEntity>?) {
            Thread.sleep(2000)//to Simulate
            placesMutableLiveData.postValue(t)
        }

        override fun onComplete() {

        }

        override fun onError(e: Throwable) {
            errorMapper(e)
            Log.e("error", e.printStackTrace().toString())

        }

    }

    private fun errorMapper(e: Throwable) {
        if (e is UnknownHostException || e is SocketException || e is SocketTimeoutException)
            responseTypeMutableLiveData.postValue(ResponseType.NETWORK)
        else
            responseTypeMutableLiveData.postValue(ResponseType.UNKNOWN)

    }

    fun getLocationObserver() = locationLiveData


    fun updateRealTimeOrNot(isRealTime: Boolean) {
        sharedPreferences.edit().putBoolean(IS_REALTIME, isRealTime).apply()
    }

    fun getUpdatedRealtimeStatues() = updateRealTimeLiveData

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}