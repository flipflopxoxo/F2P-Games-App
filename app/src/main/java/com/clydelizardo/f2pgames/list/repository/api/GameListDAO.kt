package com.clydelizardo.f2pgames.list.repository.api

import com.clydelizardo.f2pgames.list.repository.api.model.GameDetail
import com.clydelizardo.f2pgames.list.repository.api.model.GameEntry
import retrofit2.http.GET
import retrofit2.http.Query

interface GameListDAO {
    @GET("api/games")
    suspend fun getListOfFreeGames() : List<GameEntry>
}