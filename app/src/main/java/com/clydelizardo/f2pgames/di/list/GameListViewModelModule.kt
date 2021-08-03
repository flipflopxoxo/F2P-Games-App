package com.clydelizardo.f2pgames.di.list

import androidx.lifecycle.ViewModel
import com.clydelizardo.f2pgames.di.core.ViewModelKey
import com.clydelizardo.f2pgames.list.viewmodel.GameListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface GameListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GameListViewModel::class)
    fun gameListViewModel(viewModel: GameListViewModel): ViewModel
}