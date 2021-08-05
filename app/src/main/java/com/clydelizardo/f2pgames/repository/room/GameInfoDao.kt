/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room

import androidx.room.*
import com.clydelizardo.f2pgames.repository.room.model.Game
import com.clydelizardo.f2pgames.repository.room.model.GameWithScreenshots

@Dao
abstract class GameInfoDao {
    @Insert
    protected abstract suspend fun addToFavorite(entity: Game)

    @Delete
    protected abstract suspend fun removeFromFavorite(entity: Game)

    @Query("DELETE from favorite_games WHERE id = :gameId")
    abstract suspend fun removeFromFavorite(gameId: Int)

    @Transaction
    @Query("SELECT * from favorite_games")
    abstract suspend fun getFavoriteGamesWithScreenshots(): List<GameWithScreenshots>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addScreenshots(screenshots: List<Screenshot>)

    @Query("DELETE from screenshot WHERE gameId = :gameId")
    abstract suspend fun deleteScreenshotsForGame(gameId: Int)

    @Transaction
    open suspend fun addGameToFavorite(game: Game, screenshots: List<Screenshot>) {
        addToFavorite(game)
        addScreenshots(screenshots)
    }

    @Transaction
    open suspend fun removeGameFromFavorite(gameId: Int) {
        removeFromFavorite(gameId)
        deleteScreenshotsForGame(gameId)
    }

    @Query("SELECT * from favorite_games WHERE id = :gameId")
    abstract suspend fun findGameInFavorites(gameId: Int): Game?
}