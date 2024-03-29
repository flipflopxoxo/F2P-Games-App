package com.clydelizardo.f2pgames.list.usecase

import com.clydelizardo.f2pgames.repository.GameInfoRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class GetFreeGamesByApi @Inject constructor(
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