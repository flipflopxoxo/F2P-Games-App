/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clydelizardo.f2pgames.repository.room.model.Game

@Database(entities = [Game::class, Screenshot::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun gameDao(): GameInfoDao
}