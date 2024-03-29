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
    val screenshotUrls: List<String>,
    val developer: String,
    val shortDescription: String,
    val link: String,
    val thumbnail: String,
    val isFavorite: Boolean = false
) {
    val previewUrl: String?
        get() = screenshotUrls.firstOrNull()

    val hasScreenshot: Boolean
        get() = screenshotUrls.isNotEmpty()
}