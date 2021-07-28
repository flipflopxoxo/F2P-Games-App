package com.clydelizardo.f2pgames.di.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clydelizardo.f2pgames.di.core.ViewModelFactory
import com.clydelizardo.f2pgames.di.core.ViewModelKey
import com.clydelizardo.f2pgames.di.list.GameListModule
import com.clydelizardo.f2pgames.list.viewmodel.GameListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module(includes = [GameListModule::class])
interface ViewModelFactoryModule {
    @Binds
    @IntoMap
    @ViewModelKey(GameListViewModel::class)
    fun gameListViewModel(viewModel: GameListViewModel): ViewModel

    @Binds
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}