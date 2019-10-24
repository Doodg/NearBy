package com.enigma.nearby.data.store

import com.enigma.nearby.data.repos.NearByDataStore

class NearByDataStoreFactory(
    private val remoteDataStore: RemoteDataStore,
    private val cacheDataStore: CacheDataStore
) {
    fun getRemoteDataStore(): NearByDataStore {
        return remoteDataStore
    }
    fun getCacheDataStore(): NearByDataStore {
        return cacheDataStore
    }
}