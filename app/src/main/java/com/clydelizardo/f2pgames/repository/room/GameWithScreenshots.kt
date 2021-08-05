/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room

import androidx.room.Embedded
import androidx.room.Relation

data class GameWithScreenshots(
    @Embedded val game: Game,
    @Relation(
        parentColumn = "id",
        entityColumn = "gameId"
    )
    val screenshots: List<Screenshot>
)