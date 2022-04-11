/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.di.core

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import androidx.fragment.app.viewModels as viewModels1

open class BaseFragment: Fragment(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    fun <T> viewModels(): Lazy<T> {
        return this.viewModels1(factoryProducer = { viewModelFactory })
    }
}