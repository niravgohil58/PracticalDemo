package com.example.demoapp

import android.app.Application
import com.example.datingapp.di.repositoryModule
import com.example.datingapp.di.viewModelModule
import com.example.demoapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DemoApp)
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}