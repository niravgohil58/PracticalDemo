package com.example.demoapp.remote

import com.example.demoapp.responses.MainListModel
import retrofit2.http.GET

interface DemoAppService {

    @GET("/products")
    suspend fun getLists() : List<MainListModel>
}