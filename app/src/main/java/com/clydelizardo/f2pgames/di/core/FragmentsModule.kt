/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.di.core

import com.clydelizardo.f2pgames.detail.view.GameDetailFragment
import com.clydelizardo.f2pgames.di.detail.GameDetailsFragmentComponent
import com.clydelizardo.f2pgames.di.list.GameListFragmentComponent
import com.clydelizardo.f2pgames.list.view.GameListFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [GameDetailsFragmentComponent::class, GameListFragmentComponent::class])
abstract class FragmentsModule {
    @Binds
    @IntoMap
    @ClassKey(GameListFragment::class)
    abstract fun bindGameListFragmentFactory(factory: GameListFragmentComponent.Factory): AndroidInjector.Factory<*>

    @Binds
    @IntoMap
    @ClassKey(GameDetailFragment::class)
    abstract fun bindGameDetailFragmentFactory(factory: GameDetailsFragmentComponent.Factory): AndroidInjector.Factory<*>
}