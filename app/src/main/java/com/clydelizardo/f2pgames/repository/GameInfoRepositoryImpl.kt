package com.clydelizardo.f2pgames.repository

import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.repository.api.GameListDAO
import com.clydelizardo.f2pgames.repository.api.model.GameEntry
import com.clydelizardo.f2pgames.model.GameInfo
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

    override suspend fun getGameDetails(game: GameInfo): Result<GameDetail> =
        try {
            Result.success(gameListDAO.getGameDetail(game.id.toInt()).toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
}

private fun GameEntry.toGameInfo() =
    GameInfo(
        id.toString(),
        title,
        shortDescription,
        asDate(releaseDate),
        genre,
        platform,
        gameUrl,
        false,
        thumbnail = thumbnail,
        publisher = publisher,
        developer = developer
    )

private fun asDate(text: String): Date {
    return SimpleDateFormat("yyyy-MM-dd").parse(text)
        ?: throw IllegalArgumentException("Incorrect format")
}


private fun com.clydelizardo.f2pgames.repository.api.model.GameDetail.toModel() = GameDetail(
    id,
    title,
    description,
    genre,
    platform,
    publisher,
    asDate(releaseDate),
    screenshots.map {
        it.image
    }
)