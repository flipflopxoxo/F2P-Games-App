package com.clydelizardo.f2pgames.di.list

import com.clydelizardo.f2pgames.list.usecase.ChangeGameFavoriteStatus
import com.clydelizardo.f2pgames.list.usecase.ChangeGameFavoriteStatusByRepository
import com.clydelizardo.f2pgames.repository.GameInfoRepository
import com.clydelizardo.f2pgames.list.usecase.GetFreeGames
import com.clydelizardo.f2pgames.list.usecase.GetFreeGamesByApi
import com.clydelizardo.f2pgames.repository.FavoritesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class FavoriteStatusModule {
    @Provides
    fun useCase(repository: FavoritesRepository): ChangeGameFavoriteStatus {
        return ChangeGameFavoriteStatusByRepository(repository)
    }
}