package com.enigma.nearby.data.remote.retrofit

import okhttp3.Interceptor
import okhttp3.Response


private const val CLIENT_ID = "client_id"
private const val CLIENT_ID_VALUE = "E0AVU3HPLONXPYCDA24O4ED2RJFY3U1E1GO1UC1QOZMOOMCP"

private const val CLIENT_SECRET = "client_secret"
private const val CLIENT_SECRET_TOKEN = "2BFUFFZHAPCXR3LYNLPHXJIGEPBP0XU3FYLZQRZLMMA0KTHA"

private const val V_VERSION = "v"
private const val V_VERSION_VALUE = "20180323"

private const val ITEM_LIMIT = "limit"
private const val ITEM_PER_PAGE_LIMIT = "20"

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(
                CLIENT_ID,
                CLIENT_ID_VALUE
            )
            .addQueryParameter(
                CLIENT_SECRET,
                CLIENT_SECRET_TOKEN
            )
            .addQueryParameter(
                V_VERSION,
                V_VERSION_VALUE
            )
            .addQueryParameter(
                ITEM_LIMIT,
                ITEM_PER_PAGE_LIMIT
            )
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}