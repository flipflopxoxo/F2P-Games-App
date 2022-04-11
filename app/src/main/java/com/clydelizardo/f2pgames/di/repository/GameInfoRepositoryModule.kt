package com.clydelizardo.f2pgames.di.repository

import com.clydelizardo.f2pgames.repository.GameInfoRepository
import com.clydelizardo.f2pgames.repository.GameInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

//@Module
//@InstallIn(ViewModelComponent::class)
interface GameInfoRepositoryModule {
//    @Binds
    fun repository(repository: GameInfoRepositoryImpl): GameInfoRepository
}