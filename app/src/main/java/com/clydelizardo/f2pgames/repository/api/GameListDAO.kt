package com.clydelizardo.f2pgames.repository.api

import com.clydelizardo.f2pgames.repository.api.model.GameDetail
import com.clydelizardo.f2pgames.repository.api.model.GameEntry
import retrofit2.http.GET

interface GameListDAO {
    @GET("api/games")
    suspend fun getListOfFreeGames() : List<GameEntry>

    @GET("api/game")
    suspend fun getGameDetail(): GameDetail
}