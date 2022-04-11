/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.di.repository

import com.clydelizardo.f2pgames.repository.room.AppDatabase
import com.clydelizardo.f2pgames.repository.room.GameInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@Module
@DisableInstallInCheck
class GameInfoDaoModule {
    @Provides
    fun dao(database: AppDatabase): GameInfoDao {
        return database.gameDao()
    }
}