package com.clydelizardo.f2pgames.list.viewmodel.view

import java.util.*

data class GameInfo(
    val id: String,
    val name: String,
    val description: String,
    val releaseDate: Date?,
    val genre: String,
    val platform: String,
    val link: String,
    val isFavorite: Boolean,
    val thumbnail: String,
    val publisher: String,
    val developer: String
)
