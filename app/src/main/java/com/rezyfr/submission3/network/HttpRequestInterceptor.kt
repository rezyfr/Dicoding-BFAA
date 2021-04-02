package com.rezyfr.submission3.network

import com.rezyfr.submission3.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).addHeader("Authorization", "token ${BuildConfig.ACCESS_TOKEN}").build()
        Timber.d(request.toString())
        return chain.proceed(request)
    }
}