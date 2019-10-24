package com.enigma.nearby.data.remote.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient : KoinComponent {
    private const val BASE_URL = "https://api.foursquare.com/v2/"
    val retrofit: Retrofit by lazy {
        getRetrofitInstance()
    }

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getOkHttpClient(): OkHttpClient? {
        val interceptorBody = HttpLoggingInterceptor()
        interceptorBody.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addInterceptor(interceptorBody)
            .readTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }
}
