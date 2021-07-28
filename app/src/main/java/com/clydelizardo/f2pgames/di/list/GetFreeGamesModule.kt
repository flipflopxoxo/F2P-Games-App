package com.clydelizardo.f2pgames.di.list

import com.clydelizardo.f2pgames.list.repository.GameInfoRepository
import com.clydelizardo.f2pgames.list.usecase.GetFreeGames
import com.clydelizardo.f2pgames.list.usecase.GetFreeGamesByApi
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class GetFreeGamesModule {
    @Provides
    fun getFreeGameUseCase(api: GameInfoRepository): GetFreeGames {
        return GetFreeGamesByApi(api, Dispatchers.IO)
    }
}