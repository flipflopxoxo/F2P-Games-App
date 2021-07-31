package com.clydelizardo.f2pgames.repository

import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.model.GameInfo

interface GameInfoRepository {
    suspend fun getListOfGames(): Result<List<GameInfo>>

    suspend fun getGameDetails(game: GameInfo): Result<GameDetail>
}