/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class GameInfoEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val shortDescription: String,
    val releaseDate: Date?,
    val genre: String,
    val platform: String,
    val link: String,
    val thumbnail: String,
    val publisher: String,
    val developer: String
)