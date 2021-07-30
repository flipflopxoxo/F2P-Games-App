package com.clydelizardo.f2pgames.list.repository

import com.clydelizardo.f2pgames.list.repository.api.GameListDAO
import com.clydelizardo.f2pgames.list.repository.api.model.GameEntry
import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GameInfoRepositoryImpl @Inject constructor(private val gameListDAO: GameListDAO) :
    GameInfoRepository {
    override suspend fun getListOfGames(): Result<List<GameInfo>> = try {
        Result.success(gameListDAO.getListOfFreeGames().map {
            it.toGameInfo()
        })
    } catch (e: Exception) {
        Result.failure(e)
    }
}

fun GameEntry.toGameInfo() =
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

private fun GameEntry.date(): Date? {
    try {
        return SimpleDateFormat("yyyy-MM-dd").parse(releaseDate) ?: Date(0)
    } catch (e: ParseException) {

    }
    return null
}
