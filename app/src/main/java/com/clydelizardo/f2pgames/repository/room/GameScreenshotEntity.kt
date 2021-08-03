/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room

import androidx.room.Entity

@Entity(primaryKeys = ["gameId", "number"])
data class GameScreenshotEntity(
    val gameId: Int,
    val number: Int,
    val url: String
)