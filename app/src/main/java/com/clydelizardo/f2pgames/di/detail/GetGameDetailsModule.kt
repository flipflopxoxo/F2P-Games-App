package com.clydelizardo.f2pgames.di.detail

import com.clydelizardo.f2pgames.detail.usecase.GetGameDetail
import com.clydelizardo.f2pgames.detail.usecase.GetGameDetailFromRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface GetGameDetailsModule {
    @Binds
    fun useCase(useCase: GetGameDetailFromRepository): GetGameDetail
}