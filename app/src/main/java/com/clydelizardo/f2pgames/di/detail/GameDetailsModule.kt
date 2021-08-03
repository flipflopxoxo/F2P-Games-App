package com.clydelizardo.f2pgames.di.detail

import dagger.Module

@Module(includes = [GameDetailViewModelModule::class, GetGameDetailsModule::class])
interface GameDetailsModule