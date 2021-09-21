package com.clydelizardo.f2pgames.di.list

import com.clydelizardo.f2pgames.list.view.GameListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(
    modules = [
        GetFreeGamesModule::class,
        GameListViewModelModule::class,
        FavoriteStatusModule::class
    ]
)
interface GameListFragmentComponent: AndroidInjector<GameListFragment> {
    @Subcomponent.Factory
    interface Factory: AndroidInjector.Factory<GameListFragment>
}