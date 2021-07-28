package com.clydelizardo.f2pgames.list.repository

import com.clydelizardo.f2pgames.list.repository.api.GameDetailDAO
import com.clydelizardo.f2pgames.list.repository.api.model.GameDetail
import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GameInfoRepositoryImpl @Inject constructor(private val gameDetailDAO: GameDetailDAO) :
    GameInfoRepository {
    override suspend fun getListOfGames(): Result<List<GameInfo>> = try {
        Result.success(gameDetailDAO.getListOfFreeGames().map {
            it.toGameInfo()
        })
    } catch (e: Exception) {
        Result.failure(e)
    }
}

fun GameDetail.toGameInfo() =
    GameInfo(
        id.toString(),
        title,
        shortDescription,
        date(),
        genre,
        platform,
        gameUrl,
        false,
        thumbnail = thumbnail,
        publisher = publisher,
        developer = developer
    )

private fun GameDetail.date(): Date? {
    try {
        return SimpleDateFormat("yyyy-MM-dd").parse(releaseDate) ?: Date(0)
    } catch (e: ParseException) {

    }
    return null
}
