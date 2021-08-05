/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "favorite_games")
data class Game(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val shortDescription: String,
    val releaseDate: Long?,
    val genre: String,
    val platform: String,
    val link: String,
    val thumbnail: String,
    val publisher: String,
    val developer: String
)