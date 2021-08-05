/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.repository.room

import androidx.room.*

@Dao
abstract class GameInfoDao {
    @Query("SELECT * from favorite_games")
    abstract fun getFavorites(): List<Game>

    @Insert
    abstract fun addToFavorite(entity: Game)

    @Delete
    abstract fun removeFromFavorite(entity: Game)

    @Query("SELECT * from favorite_games")
    abstract fun getFavoriteGamesWithScreenshots(): List<GameWithScreenshots>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addScreenshots(screenshots: List<Screenshot>)

    @Query("DELETE from screenshot WHERE gameId = :gameId")
    abstract fun deleteScreenshotsForGame(gameId: Int)

    @Transaction
    open fun addGameToFavorite(game: Game, screenshots: List<Screenshot>) {
        addToFavorite(game)
        addScreenshots(screenshots)
    }

    @Transaction
    open fun removeGameFromFavorite(game: Game) {
        removeFromFavorite(game)
        deleteScreenshotsForGame(game.id)
    }
}