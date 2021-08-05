/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room

import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.repository.room.model.Game
import java.util.*

fun GameDetail.toGameAndScreenshot(): Pair<Game, List<Screenshot>> {
    return Pair(toGame(), extractScreenshots())
}

private fun GameDetail.toGame(): Game {
    return Game(
        id = id,
        name = title,
        description = description,
        shortDescription = shortDescription,
        releaseDate = releaseDate.time,
        genre = genre,
        platform = platform,
        link = link,
        thumbnail = thumbnail,
        publisher = publisher,
        developer = developer
    )
}

private fun GameDetail.extractScreenshots(): List<Screenshot> {
    return this.screenshotUrls.mapIndexed { index, url ->
        Screenshot(
            gameId = id,
            number = index,
            url = url
        )
    }
}

fun Pair<Game, List<Screenshot>>.toGameDetail(): GameDetail {
    val game = first
    val screenshotList = second
    return GameDetail(
        id = game.id,
        title = game.name,
        description = game.description,
        genre = game.genre,
        platform = game.platform,
        publisher = game.publisher,
        releaseDate = game.releaseDate?.let { Date(it) } ?: Date(0),
        screenshotUrls = screenshotList.sortedBy { it.number }.map {
            it.url
        },
        developer = game.developer,
        shortDescription = game.shortDescription,
        link = game.link,
        thumbnail = game.thumbnail
    )
}