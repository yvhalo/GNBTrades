package com.gnb.gnbtrades.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("Accept", "application/json").build()
        return chain.proceed(request)
    }
}