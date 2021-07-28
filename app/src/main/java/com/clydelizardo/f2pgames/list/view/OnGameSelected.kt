package com.clydelizardo.f2pgames.list.view

import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo

interface OnGameSelected {
    fun onGameSelected(game: GameInfo)
}