/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room

import androidx.room.Entity
import androidx.room.ForeignKey
import com.clydelizardo.f2pgames.repository.room.model.Game

@Entity(primaryKeys = ["gameId", "number"],
foreignKeys = [ForeignKey(entity = Game::class, parentColumns = ["id"], childColumns = ["gameId"])])
data class Screenshot(
    val gameId: Int,
    val number: Int,
    val url: String
)