package com.clydelizardo.f2pgames.repository

import com.clydelizardo.f2pgames.model.GameInfo

interface GameInfoRepository {
    suspend fun getListOfGames(): Result<List<GameInfo>>
}