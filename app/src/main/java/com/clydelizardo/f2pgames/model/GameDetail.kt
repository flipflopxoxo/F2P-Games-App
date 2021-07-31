package com.clydelizardo.f2pgames.model

import java.util.*

data class GameDetail(
    val id: Int,
    val title: String,
    val description: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val releaseDate: Date,
    val screenshotUrls: List<String>
)