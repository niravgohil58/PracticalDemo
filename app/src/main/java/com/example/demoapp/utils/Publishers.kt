package com.example.demoapp.utils

import io.reactivex.subjects.PublishSubject

object Publishers {

    val bottomNavState = PublishSubject.create<Boolean>()
}