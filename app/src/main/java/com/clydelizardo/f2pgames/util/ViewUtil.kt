package com.clydelizardo.f2pgames.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDisplayFormat(locale: Locale): String {
    return SimpleDateFormat("yyyy-MM-dd", locale).format(this)
}