package com.example.demoapp.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RetroInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest: Request.Builder = chain.request()
            .newBuilder()
            .method(request.method, request.body)

        return chain.proceed(newRequest.build())
    }
}