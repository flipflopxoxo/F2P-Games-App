/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room.model

import androidx.room.Embedded
import androidx.room.Relation
import com.clydelizardo.f2pgames.repository.room.Screenshot

data class GameWithScreenshots(
    @Embedded val game: Game,
    @Relation(
        parentColumn = "id",
        entityColumn = "gameId"
    )
    val screenshots: List<Screenshot>
)