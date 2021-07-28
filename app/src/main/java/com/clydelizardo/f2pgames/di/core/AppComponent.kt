package com.clydelizardo.f2pgames.di.core

import androidx.lifecycle.ViewModelProvider
import com.clydelizardo.f2pgames.MainActivity
import com.clydelizardo.f2pgames.di.core.viewmodel.ViewModelFactoryModule
import com.clydelizardo.f2pgames.di.list.GameListModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}