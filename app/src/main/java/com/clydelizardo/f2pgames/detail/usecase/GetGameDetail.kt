package com.clydelizardo.f2pgames.detail.usecase

import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.model.GameInfo

fun interface GetGameDetail {
    suspend operator fun invoke(gameInfo: GameInfo): GetGameDetailResult
}

sealed class GetGameDetailResult {
    data class Success(val value: GameDetail) : GetGameDetailResult()
    object Failure : GetGameDetailResult()
}