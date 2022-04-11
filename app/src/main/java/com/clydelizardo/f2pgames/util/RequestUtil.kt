/*
 * Copyright (c) 2022 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.util

sealed class Status<out T> {
    data class Success<out T>(val content: T): Status<T>()
    class Failure<out T> : Status<T>()
    class Loading<out T>: Status<T>()
}