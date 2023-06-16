package com.example.demoapp.utils

import com.example.demoapp.responses.MainListModel

sealed class ResponseList<T>(
    val msg: String? = null,
    val data: List<MainListModel>? = null,
) {

    class Loading : ResponseList<Nothing>()

    class Success<T>(data: List<MainListModel>?) : ResponseList<T>(data = data)

    class Failed(msg: String?) : ResponseList<Nothing>(msg = msg)
}

