package com.clydelizardo.f2pgames.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDisplayFormat(): String {
    return SimpleDateFormat("yyyy-MM-dd").format(this)
}