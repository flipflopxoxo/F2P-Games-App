/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.api

import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.model.GameInfo
import com.clydelizardo.f2pgames.repository.api.model.GameEntry
import java.text.SimpleDateFormat
import java.util.*

fun GameEntry.toGameInfo() =
    GameInfo(
        id,
        title,
        shortDescription,
        asDate(releaseDate),
        genre,
        platform,
        gameUrl,
        false,
        thumbnail = thumbnail,
        publisher = publisher,
        developer = developer
    )

fun com.clydelizardo.f2pgames.repository.api.model.GameDetail.toModel() = GameDetail(
    id = id,
    title = title,
    description = description,
    genre = genre,
    platform = platform,
    publisher = publisher,
    releaseDate = asDate(releaseDate),
    screenshotUrls = screenshots.map {
        it.image
    },
    developer = developer,
    shortDescription = shortDescription,
    link = this.gameUrl,
    thumbnail = thumbnail
)

fun asDate(text: String): Date {
    return SimpleDateFormat("yyyy-MM-dd").parse(text)
        ?: throw IllegalArgumentException("Incorrect format")
}