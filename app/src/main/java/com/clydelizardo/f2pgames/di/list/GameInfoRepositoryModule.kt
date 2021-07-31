package com.clydelizardo.f2pgames.di.list

import com.clydelizardo.f2pgames.repository.GameInfoRepository
import com.clydelizardo.f2pgames.repository.GameInfoRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface GameInfoRepositoryModule {
    @Binds
    fun repository(repository: GameInfoRepositoryImpl): GameInfoRepository
}