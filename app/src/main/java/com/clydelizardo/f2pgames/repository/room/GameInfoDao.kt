/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room

import androidx.room.*

@Dao
abstract class GameInfoDao {
    @Query("SELECT * from favorite_games")
    abstract suspend fun getFavorites(): List<Game>

    @Insert
    abstract suspend fun addToFavorite(entity: Game)

    @Delete
    abstract suspend fun removeFromFavorite(entity: Game)

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
    open suspend fun removeGameFromFavorite(game: Game) {
        removeFromFavorite(game)
        deleteScreenshotsForGame(game.id)
    }

    @Query("SELECT * from favorite_games WHERE id = :gameId")
    abstract suspend fun findGameInFavorites(gameId: Int): Game?
}