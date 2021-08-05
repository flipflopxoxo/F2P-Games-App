/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository

import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.model.GameInfo
import com.clydelizardo.f2pgames.repository.api.GameInfoService
import com.clydelizardo.f2pgames.repository.api.toGameInfo
import com.clydelizardo.f2pgames.repository.api.toModel
import com.clydelizardo.f2pgames.repository.room.GameInfoDao
import com.clydelizardo.f2pgames.repository.room.toGameAndScreenshot
import java.util.*
import javax.inject.Inject
import kotlin.Exception

class GameInfoWithFavoritesRepository @Inject constructor(
    private val service: GameInfoService,
    private val dao: GameInfoDao
) : GameInfoRepository, FavoritesRepository {
    override suspend fun getFavoriteGames(): List<GameInfo> {
        return dao.getFavoriteGamesWithScreenshots().map {
            GameInfo(
                id = it.game.id,
                name = it.game.name,
                description = it.game.shortDescription,
                releaseDate = it.game.releaseDate?.let { dateAsLong -> return@let Date(dateAsLong) },
                genre = it.game.genre,
                platform = it.game.platform,
                link = it.game.link,
                isFavorite = true,
                thumbnail = it.game.thumbnail,
                publisher = it.game.publisher,
                developer = it.game.developer
            )
        }
    }

    override suspend fun addGameToFavorites(gameId: Int): Boolean {
        val gameDetail = service.getGameDetail(gameId).toModel()
        return try {
            addGameToFavorites(gameDetail)
            true
        } catch (_: Exception) {
            false
        }
    }

    override suspend fun addGameToFavorites(game: GameDetail): Boolean {
        val (game, screenshotList) = game.toGameAndScreenshot()
        return try {
            dao.addGameToFavorite(game, screenshotList)
            true
        } catch (_: Exception) {
            false
        }
    }

    override suspend fun removeGameFromFavorites(gameId: Int): Boolean {
        return try {
            dao.removeGameFromFavorite(gameId)
            true
        } catch (_: Exception) {
            false
        }
    }

    override suspend fun removeGameFromFavorites(game: GameDetail): Boolean {
        return removeGameFromFavorites(game.id)
    }

    override suspend fun isGameFavorite(gameId: Int): Boolean {
        return dao.findGameInFavorites(gameId) != null
    }

    override suspend fun getListOfGames(): Result<List<GameInfo>> {
        return try {
            val listOfFreeGames = service.getListOfFreeGames()
            val favoriteGames = getFavoriteGames()
            val map = listOfFreeGames.map {
                it.toGameInfo()
                    .copy(isFavorite = favoriteGames.firstOrNull { gameInfo -> gameInfo.id == it.id } != null)
            }
            Result.success(map)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getGameDetails(game: GameInfo): Result<GameDetail> {
        return try {
            val serviceResult = service.getGameDetail(game.id)
            val gameDetail =
                serviceResult.toModel().copy(isFavorite = dao.findGameInFavorites(game.id) != null)
            Result.success(gameDetail)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}