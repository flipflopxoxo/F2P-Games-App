package com.clydelizardo.f2pgames.di.detail

import androidx.lifecycle.ViewModel
import com.clydelizardo.f2pgames.detail.viewmodel.GameDetailViewModel
import com.clydelizardo.f2pgames.di.core.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.assisted.AssistedFactory
import dagger.multibindings.IntoMap

@AssistedFactory
interface GameDetailViewModelFactory {
    @Binds
    @IntoMap
    @ViewModelKey(GameDetailViewModel::class)
    fun viewModel(viewModel: GameDetailViewModel): ViewModel
}