package com.clydelizardo.f2pgames.list.usecase

import com.clydelizardo.f2pgames.list.repository.GameInfoRepository
import com.clydelizardo.f2pgames.list.repository.api.GameDetailDAO
import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import kotlin.coroutines.CoroutineContext

class GetFreeGamesByApi(
    private val repository: GameInfoRepository,
    private val context: CoroutineContext
) : GetFreeGames {
    override suspend fun invoke(): GetFreeGamesResult {
        val freeGamesList = withContext(context) {
            repository.getListOfGames()
        }
        return when {
            freeGamesList.isSuccess -> {
                GetFreeGamesResult.Success(freeGamesList.getOrThrow())
            }
            else -> {
                GetFreeGamesResult.Failure
            }
        }
    }
}