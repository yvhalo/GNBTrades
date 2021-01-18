package com.gnb.gnbtrades.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor {

    /**
     * Intercept.
     * add header to all petitions
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("Accept", "application/json").build()
        return chain.proceed(request)
    }
}