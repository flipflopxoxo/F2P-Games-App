/*
 * Copyright (c) 2022 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.list.viewmodel

import com.clydelizardo.f2pgames.model.GameInfo

sealed class GameListState(
    val isRefreshing: Boolean = false,
    val gameList: List<GameInfo> = emptyList()
) {
    object Loading : GameListState(isRefreshing = true)
    object Failure : GameListState()
    data class Success(val list: List<GameInfo>) : GameListState(gameList = list)
    data class Refreshing(val list: List<GameInfo>) : GameListState(isRefreshing = true, gameList = list)
    data class Updating(val list: List<GameInfo>) : GameListState(isRefreshing = true, gameList = list)
    data class FailedRefresh(val list: List<GameInfo>) : GameListState(gameList = list)
    data class FailedUpdate(val list: List<GameInfo>) : GameListState(gameList = list)
}