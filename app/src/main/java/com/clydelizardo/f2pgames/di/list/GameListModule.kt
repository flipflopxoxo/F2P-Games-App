package com.clydelizardo.f2pgames.di.list

import dagger.Module

@Module(includes = [GameInfoRepositoryModule::class, GameDetailDAOModule::class, GetFreeGamesModule::class])
interface GameListModule {

}