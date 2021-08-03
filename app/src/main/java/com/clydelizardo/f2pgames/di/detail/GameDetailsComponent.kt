/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.di.detail

import com.clydelizardo.f2pgames.detail.view.GameDetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [GameDetailViewModelModule::class, GetGameDetailsModule::class])
interface GameDetailsComponent {
    fun inject(fragment: GameDetailFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): GameDetailsComponent
    }
}