package com.example.datingapp.di

import com.example.demoapp.repository.ListRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        ListRepository(get())
    }
}