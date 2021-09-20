/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.list.view

import com.clydelizardo.f2pgames.model.GameInfo

interface LongPressListener {
    fun onLongPress(gameInfo: GameInfo)
}