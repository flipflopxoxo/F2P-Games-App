/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames

import android.app.Application
import com.clydelizardo.f2pgames.di.core.AppComponent
import com.clydelizardo.f2pgames.di.core.DaggerAppComponent

class GameApplication : Application() {
    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}