package com.clydelizardo.f2pgames.di.list

import com.clydelizardo.f2pgames.di.repository.GameInfoRepositoryModule
import com.clydelizardo.f2pgames.di.repository.GameInfoServiceModule
import com.clydelizardo.f2pgames.list.view.GameListFragment
import dagger.Module
import dagger.Subcomponent

@Subcomponent(modules = [
    GetFreeGamesModule::class,
    GameListViewModelModule::class
])
interface GameListComponent {
    fun inject(fragment: GameListFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): GameListComponent
    }
}