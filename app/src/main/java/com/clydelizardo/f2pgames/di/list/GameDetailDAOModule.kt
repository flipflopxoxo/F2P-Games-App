package com.clydelizardo.f2pgames.di.list

import com.clydelizardo.f2pgames.list.repository.api.GameDetailDAO
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
abstract class GameDetailDAOModule {
    companion object {
        @Provides
        @JvmStatic
        fun gameDetailDao(): GameDetailDAO = Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GameDetailDAO::class.java)
    }
}