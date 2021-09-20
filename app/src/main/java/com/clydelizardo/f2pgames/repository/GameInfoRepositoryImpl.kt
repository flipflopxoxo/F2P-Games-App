/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository

import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.repository.api.GameInfoService
import com.clydelizardo.f2pgames.model.GameInfo
import com.clydelizardo.f2pgames.repository.api.toGameInfo
import com.clydelizardo.f2pgames.repository.api.toModel
import javax.inject.Inject

class GameInfoRepositoryImpl @Inject constructor(private val gameInfoService: GameInfoService) :
    GameInfoRepository {
    override suspend fun getListOfGames(): Result<List<GameInfo>> = try {
        Result.success(gameInfoService.getListOfFreeGames().map {
            it.toGameInfo()
        })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getGameDetails(game: GameInfo): Result<GameDetail> =
        try {
            Result.success(gameInfoService.getGameDetail(game.id).toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
}
