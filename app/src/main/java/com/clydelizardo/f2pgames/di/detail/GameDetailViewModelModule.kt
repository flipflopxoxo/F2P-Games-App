package com.clydelizardo.f2pgames.di.detail

import androidx.lifecycle.ViewModel
import com.clydelizardo.f2pgames.detail.viewmodel.GameDetailViewModel
import com.clydelizardo.f2pgames.di.core.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface GameDetailViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GameDetailViewModel::class)
    fun viewModel(viewModel: GameDetailViewModel): ViewModel
}