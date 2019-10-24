package com.enigma.nearby.data.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enigma.nearby.data.cache.VenueItemEntity

@Database(entities = [VenueItemEntity::class], version = 1)
abstract class NearByDataBase : RoomDatabase() {

    abstract fun cachedPlacesItem(): PlacesDao

    companion object {
        private var INSTANCE: NearByDataBase? = null
        private val lock = Any()
        fun getInstance(context: Context): NearByDataBase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NearByDataBase::class.java,
                            DataBaseConstant.DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration(). build ()
                    }
                    return INSTANCE as NearByDataBase
                }
            }
            return INSTANCE as NearByDataBase
        }
    }
}