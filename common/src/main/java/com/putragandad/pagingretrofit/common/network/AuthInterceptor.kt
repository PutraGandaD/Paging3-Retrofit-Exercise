package com.putragandad.pagingretrofit.common.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val reqBuilder = original.newBuilder()
            .header("Accept", "application/json")
        val request = reqBuilder.build()
        return chain.proceed(request)
    }
}