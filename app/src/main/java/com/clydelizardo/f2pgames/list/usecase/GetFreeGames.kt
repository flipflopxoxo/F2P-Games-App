package com.clydelizardo.f2pgames.list.usecase

import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo

fun interface GetFreeGames {
    suspend operator fun invoke(): GetFreeGamesResult
}

sealed class GetFreeGamesResult {
    data class Success(val list: List<GameInfo>): GetFreeGamesResult()
    object Failure : GetFreeGamesResult()
}
