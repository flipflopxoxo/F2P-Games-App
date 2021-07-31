package com.clydelizardo.f2pgames.di.list

import com.clydelizardo.f2pgames.repository.api.GameListDAO
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class GameDetailDAOModule {
    companion object {
        @Provides
        @JvmStatic
        fun gameDetailDao(): GameListDAO = Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GameListDAO::class.java)
    }
}