package com.clydelizardo.f2pgames.di.list

import com.clydelizardo.f2pgames.list.repository.GameInfoRepository
import com.clydelizardo.f2pgames.list.repository.GameInfoRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface GameInfoRepositoryModule {
    @Binds
    fun repository(repository: GameInfoRepositoryImpl): GameInfoRepository
}