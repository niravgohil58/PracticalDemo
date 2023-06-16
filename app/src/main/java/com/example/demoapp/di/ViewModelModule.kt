package com.example.datingapp.di

import com.example.demoapp.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ListViewModel(get())
    }
}