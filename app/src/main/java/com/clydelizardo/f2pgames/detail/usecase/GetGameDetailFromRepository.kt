package com.clydelizardo.f2pgames.detail.usecase

import com.clydelizardo.f2pgames.model.GameInfo
import com.clydelizardo.f2pgames.repository.GameInfoRepository

class GetGameDetailFromRepository(private val repository: GameInfoRepository) : GetGameDetail {
    override suspend fun invoke(gameInfo: GameInfo): GetGameDetailResult {
        val detailResult = repository.getGameDetails(gameInfo)
        return when {
            detailResult.isSuccess -> GetGameDetailResult.Success(detailResult.getOrThrow())
            else -> GetGameDetailResult.Failure
        }
    }
}