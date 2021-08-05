/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository

import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.model.GameInfo

interface FavoritesRepository {
    suspend fun getFavoriteGames(): List<GameInfo>

    /**
     * Returns whether the operation is a success
     */
    suspend fun addGameToFavorites(gameId: Int): Boolean
    suspend fun addGameToFavorites(game: GameDetail): Boolean

    /**
     * Returns whether the operation is a success
     */
    suspend fun removeGameFromFavorites(gameId: Int): Boolean
    suspend fun removeGameFromFavorites(game: GameDetail): Boolean
    suspend fun isGameFavorite(gameId: Int): Boolean
}