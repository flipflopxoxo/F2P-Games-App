package com.clydelizardo.f2pgames.di.repository

import com.clydelizardo.f2pgames.repository.GameInfoRepository
import com.clydelizardo.f2pgames.repository.GameInfoRepositoryImpl
import com.clydelizardo.f2pgames.repository.GameInfoWithFavoritesRepository
import dagger.Binds
import dagger.Module

@Module
interface GameInfoFavoritesRepositoryModule {
    @Binds
    fun repository(repository: GameInfoWithFavoritesRepository): GameInfoRepository
}