package com.clydelizardo.f2pgames.di.list

import android.os.Bundle
import androidx.savedstate.SavedStateRegistryOwner
import com.clydelizardo.f2pgames.di.repository.GameInfoRepositoryModule
import com.clydelizardo.f2pgames.di.repository.GameInfoServiceModule
import com.clydelizardo.f2pgames.list.view.GameListFragment
import dagger.BindsInstance
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
        @BindsInstance
        fun savedStateRegistryOwner(owner: SavedStateRegistryOwner): Factory

        @BindsInstance
        fun defaultArgs(args: Bundle?)

        fun create(): GameListComponent
    }
}