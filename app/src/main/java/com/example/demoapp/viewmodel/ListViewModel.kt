package com.example.demoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demoapp.repository.ListRepository
import com.example.demoapp.utils.ResponseList

class ListViewModel(private val userRepository: ListRepository) : ViewModel() {

    fun getLists() = liveData {
        emit(ResponseList.Loading())
        try {
            emit(ResponseList.Success(userRepository.getLists()))
        } catch (e: Exception) {
            emit(ResponseList.Failed(e.message))
        }
    }
}