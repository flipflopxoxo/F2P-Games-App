/*
 * Copyright (c) 2022 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MediatorLiveData
import kotlin.experimental.ExperimentalTypeInference

inline fun <reified U> LiveData<*>.filterIsInstance(): LiveData<U> {
    val mediatorLiveData = MediatorLiveData<U>()
    mediatorLiveData.addSource(this) {
        if (it is U) {
            mediatorLiveData.value = it
        }
    }
    return mediatorLiveData
}

@OptIn(ExperimentalTypeInference::class)
fun <T> refreshableLiveData(@BuilderInference block: suspend LiveDataScope<T>.() -> Unit): RefreshableLiveData<T> {
    return RefreshableLiveData(block)
}