package com.example.demoapp.repository

import com.example.demoapp.remote.DemoAppService

class ListRepository(private val demoAppService: DemoAppService) {

    suspend fun getLists() = demoAppService.getLists()

}