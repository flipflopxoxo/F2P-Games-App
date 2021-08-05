/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.di.repository

import android.app.Application
import androidx.room.Room
import com.clydelizardo.f2pgames.repository.room.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class AppDatabaseModule {
    @Provides
    fun database(appContext: Application): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "free_games_db")
            .build()
    }
}