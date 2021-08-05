/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Game::class, Screenshot::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun gameDao(): GameInfoDao
}