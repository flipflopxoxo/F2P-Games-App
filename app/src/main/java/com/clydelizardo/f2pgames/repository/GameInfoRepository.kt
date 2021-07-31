package com.clydelizardo.f2pgames.repository

import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo

interface GameInfoRepository {
    suspend fun getListOfGames(): Result<List<GameInfo>>
}