package com.clydelizardo.f2pgames.list.view

import com.clydelizardo.f2pgames.model.GameInfo

interface OnGameSelected {
    fun onGameSelected(game: GameInfo)
}