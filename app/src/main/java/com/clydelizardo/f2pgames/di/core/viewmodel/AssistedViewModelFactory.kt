/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.di.core.viewmodel

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface AssistedViewModelFactory<VM: ViewModel> {
    fun create(defaultArgs: Bundle?, savedStateHandle: SavedStateHandle): VM
}