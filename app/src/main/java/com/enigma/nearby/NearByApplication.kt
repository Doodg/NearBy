package com.enigma.nearby

import android.app.Application
import com.enigma.nearby.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NearByApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        setUpKOIN()
    }

    private fun setUpKOIN() {
        startKoin {
            androidContext(this@NearByApplication)
            modules(
                listOf(
                    appModules
                )
            )
        }
    }


    companion object {
        private var instance: NearByApplication? = null
        fun getInstance(): NearByApplication {
            if (instance == null)
                throw IllegalStateException("Something went horribly wrong!!, no application attached!")
            return instance as NearByApplication
        }

    }
}