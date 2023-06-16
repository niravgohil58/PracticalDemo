package com.example.demoapp.di

import com.example.demoapp.BuildConfig
import com.example.demoapp.remote.Constant
import com.example.demoapp.remote.DemoAppService
import com.example.demoapp.remote.RetroInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        RetroInterceptor()
    }

    single {
        val timeout = 1L
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(timeout, TimeUnit.MINUTES)
        builder.readTimeout(timeout, TimeUnit.MINUTES)
        builder.writeTimeout(timeout, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(logging)
        }

        val interceptor: RetroInterceptor = get()
        builder.addInterceptor(interceptor)

        builder.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(DemoAppService::class.java)
    }
}