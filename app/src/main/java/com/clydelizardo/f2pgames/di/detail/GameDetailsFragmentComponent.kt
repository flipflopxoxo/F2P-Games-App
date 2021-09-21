/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.di.detail

import com.clydelizardo.f2pgames.detail.view.GameDetailFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [GameDetailViewModelModule::class, GetGameDetailsModule::class])
interface GameDetailsFragmentComponent: AndroidInjector<GameDetailFragment> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<GameDetailFragment>
}