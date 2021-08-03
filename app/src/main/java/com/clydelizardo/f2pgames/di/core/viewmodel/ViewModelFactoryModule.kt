package com.clydelizardo.f2pgames.di.core.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.clydelizardo.f2pgames.di.core.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}