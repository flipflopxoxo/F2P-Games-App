package com.clydelizardo.f2pgames.list.repository.api

import com.clydelizardo.f2pgames.list.repository.api.model.GameDetail
import retrofit2.http.GET

interface GameDetailDAO {
    @GET("api/games")
    suspend fun getListOfFreeGames() : List<GameDetail>
}