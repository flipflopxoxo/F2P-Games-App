package com.clydelizardo.f2pgames.list.repository

import com.clydelizardo.f2pgames.list.repository.model.GameDetail
import retrofit2.http.GET

interface GameDetailRepository {
    @GET("api/games")
    suspend fun getListOfFreeGames() : List<GameDetail>
}