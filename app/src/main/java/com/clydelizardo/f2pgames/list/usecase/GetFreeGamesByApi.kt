package com.clydelizardo.f2pgames.list.usecase

import com.clydelizardo.f2pgames.list.repository.api.GameDetailDAO
import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import kotlin.coroutines.CoroutineContext

class GetFreeGamesByApi(
    private val gameDetailDAO: GameDetailDAO,
    private val context: CoroutineContext
) : GetFreeGames {
    override suspend fun invoke(): GetFreeGamesResult {
        try {
            val freeGamesList = withContext(context) {
                gameDetailDAO.getListOfFreeGames()
            }
            return GetFreeGamesResult.Success(freeGamesList.map {
                GameInfo(
                    it.title,
                    it.shortDescription,
                    SimpleDateFormat().parse(it.releaseDate),
                    it.genre,
                    it.platform,
                    it.gameUrl,
                    false
                )
            })
        } catch (e: Exception) {
            return GetFreeGamesResult.Failure
        }
    }
}