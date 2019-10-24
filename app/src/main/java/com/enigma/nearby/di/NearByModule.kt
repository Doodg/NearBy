package com.enigma.nearby.di

import android.content.Context
import android.content.SharedPreferences
import com.enigma.nearby.LiveData.UpdateRealTimeLiveData
import com.enigma.nearby.LiveData.LocationUpdateLiveData
import com.enigma.nearby.LiveData.NetworkStatusLiveData
import com.enigma.nearby.R
import com.enigma.nearby.data.PlacesItemMapper
import com.enigma.nearby.data.store.NearByDataRepoImpl
import com.enigma.nearby.data.store.NearByDataStoreFactory
import com.enigma.nearby.data.remote.NearByRemoteImpl
import com.enigma.nearby.data.UseCaseRepository
import com.enigma.nearby.data.cache.NearByCacheImpl
import com.enigma.nearby.data.cache.room.NearByDataBase
import com.enigma.nearby.data.remote.retrofit.NearByService
import com.enigma.nearby.data.remote.retrofit.RetrofitClient
import com.enigma.nearby.data.repos.NearByCache
import com.enigma.nearby.data.store.RemoteDataStore
import com.enigma.nearby.data.repos.NearByRemote
import com.enigma.nearby.data.store.CacheDataStore
import com.enigma.nearby.viewmodel.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {


    //dataBase
    single { NearByDataBase.getInstance(context = androidContext()) }
    //dataStore
    single<NearByRemote> { NearByRemoteImpl(get()) }
    single<NearByCache> { NearByCacheImpl(get()) }

    single { RemoteDataStore(get()) }
    single { CacheDataStore(get()) }
    single { NearByDataStoreFactory(get(), get()) }
    //dataMapper
    factory { PlacesItemMapper() }


    //retrofit
    single { RetrofitClient.retrofit.create(NearByService::class.java) }

//Data ReposImpl
    single<UseCaseRepository> { NearByDataRepoImpl(get(), get()) }

    //sharedPrefrence
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            androidContext().getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    //Custome LiveData
    single { LocationUpdateLiveData(get()) }
    single { UpdateRealTimeLiveData(get()) }
    single { NetworkStatusLiveData(androidContext()) }

    //viewModel
    viewModel { MainActivityViewModel(get(), get(), get(), get()) }

}