package com.clydelizardo.f2pgames.di.repository

import com.clydelizardo.f2pgames.repository.FavoritesRepository
import com.clydelizardo.f2pgames.repository.GameInfoRepository
import com.clydelizardo.f2pgames.repository.GameInfoWithFavoritesRepository
import dagger.Binds
import dagger.Module

@Module
interface GameInfoFavoritesRepositoryModule {
    @Binds
    fun gameInfoRepository(repository: GameInfoWithFavoritesRepository): GameInfoRepository
    @Binds
    fun favoritesRepository(repository: GameInfoWithFavoritesRepository): FavoritesRepository
}