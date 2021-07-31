package com.clydelizardo.f2pgames.di.list

import com.clydelizardo.f2pgames.di.repository.GameInfoRepositoryModule
import com.clydelizardo.f2pgames.di.repository.GameInfoServiceModule
import dagger.Module

@Module(includes = [GameInfoRepositoryModule::class, GameInfoServiceModule::class, GetFreeGamesModule::class])
interface GameListModule {
}