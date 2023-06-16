package com.example.demoapp.responses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainListModel(
    val image: String? = null,
    val price: Double? = null,
    val rating: Rating? = null,
    val description: String? = null,
    val id: Int? = null,
    val title: String? = null,
    val category: String? = null
) : Parcelable

@Parcelize
data class Rating(
    val rate: Double? = null,
    val count: Int? = null
) : Parcelable
