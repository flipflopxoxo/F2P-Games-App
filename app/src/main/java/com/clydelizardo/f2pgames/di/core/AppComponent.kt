package com.clydelizardo.f2pgames.di.core

import com.clydelizardo.f2pgames.detail.view.GameDetailFragment
import com.clydelizardo.f2pgames.di.core.viewmodel.ViewModelFactoryModule
import com.clydelizardo.f2pgames.di.detail.GameDetailsComponent
import com.clydelizardo.f2pgames.di.list.GameListComponent
import com.clydelizardo.f2pgames.di.repository.GameInfoRepositoryModule
import com.clydelizardo.f2pgames.di.repository.GameInfoServiceModule
import com.clydelizardo.f2pgames.list.view.GameListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class, GameInfoRepositoryModule::class, GameInfoServiceModule::class])
interface AppComponent {
    fun gameListComponent(): GameListComponent.Factory
    fun gameDetailComponent(): GameDetailsComponent.Factory
}