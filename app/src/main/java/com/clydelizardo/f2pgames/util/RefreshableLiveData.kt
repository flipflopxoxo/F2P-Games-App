/*
 * Copyright (c) 2022 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.util

import androidx.lifecycle.*

class RefreshableLiveData<T>(block: suspend LiveDataScope<T>.() -> Unit) : MediatorLiveData<T>() {
    private val counter = MutableLiveData(Unit)
    init {
        addSource(counter.switchMap {
            liveData(block = block)
        }) {
            value = it
        }
    }

    fun refresh() {
        counter.value = Unit
    }
}