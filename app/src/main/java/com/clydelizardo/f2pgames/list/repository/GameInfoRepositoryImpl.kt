package com.clydelizardo.f2pgames.list.repository

import com.clydelizardo.f2pgames.list.repository.api.GameDetailDAO
import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo
import java.text.SimpleDateFormat
import java.util.*

class GameInfoRepositoryImpl(private val gameDetailDAO: GameDetailDAO) : GameInfoRepository {
    override suspend fun getListOfGames(): Result<List<GameInfo>> = try {
        Result.success(gameDetailDAO.getListOfFreeGames().map {
            GameInfo(
                it.title,
                it.shortDescription,
                SimpleDateFormat().parse(it.releaseDate) ?: Date(0),
                it.genre,
                it.platform,
                it.gameUrl,
                false
            )
        })
    } catch (e: Exception) {
        Result.failure(e)
    }
}

