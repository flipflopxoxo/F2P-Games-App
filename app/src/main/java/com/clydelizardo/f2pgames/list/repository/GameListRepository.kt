package com.clydelizardo.f2pgames.list.repository

import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo

interface GameListRepository {
    suspend fun getListOfGames(): List<GameInfo>
}