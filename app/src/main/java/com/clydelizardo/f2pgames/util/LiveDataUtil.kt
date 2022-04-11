/*
 * Copyright (c) 2022 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

inline fun <reified U> LiveData<*>.filterIsInstance(): LiveData<U> {
    val mediatorLiveData = MediatorLiveData<U>()
    mediatorLiveData.addSource(this) {
        if (it is U) {
            mediatorLiveData.value = it
        }
    }
    return mediatorLiveData
}