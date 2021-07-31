package com.clydelizardo.f2pgames.di.list

import com.clydelizardo.f2pgames.repository.api.GameInfoService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
abstract class GameDetailServiceModule {
    companion object {
        @Provides
        @JvmStatic
        fun gameInfoService(): GameInfoService = Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GameInfoService::class.java)
    }
}