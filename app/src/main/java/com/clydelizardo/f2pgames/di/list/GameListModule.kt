package com.clydelizardo.f2pgames.di.list

import dagger.Module

@Module(includes = [GameInfoRepositoryModule::class, GameDetailServiceModule::class, GetFreeGamesModule::class])
interface GameListModule {

}