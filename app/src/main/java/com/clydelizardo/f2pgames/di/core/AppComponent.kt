package com.clydelizardo.f2pgames.di.core

import com.clydelizardo.f2pgames.di.repository.AppDatabaseModule
import com.clydelizardo.f2pgames.di.repository.GameInfoDaoModule
import com.clydelizardo.f2pgames.di.repository.GameInfoServiceModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [AppDatabaseModule::class, GameInfoDaoModule::class, GameInfoServiceModule::class])
@InstallIn(SingletonComponent::class)
interface AppComponent {}