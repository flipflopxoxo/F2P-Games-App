/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.list.usecase

import com.clydelizardo.f2pgames.model.GameInfo

fun interface ChangeGameFavoriteStatus {
    suspend operator fun invoke(
        gameInfo: GameInfo,
        newFavoriteStatus: Boolean
    ): FavoriteStatusResult
}

sealed class FavoriteStatusResult {
    data class Success(val updatedGameInfo: GameInfo) : FavoriteStatusResult()
    object Failure : FavoriteStatusResult()
}