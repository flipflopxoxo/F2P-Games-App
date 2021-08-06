/*
 * Copyright (c) 2021 Clyde Lizardo
 */

package com.clydelizardo.f2pgames.list.usecase

import com.clydelizardo.f2pgames.model.GameInfo
import com.clydelizardo.f2pgames.repository.FavoritesRepository
import javax.inject.Inject

class ChangeGameFavoriteStatusByRepository @Inject constructor(private val favoritesRepository: FavoritesRepository) :
    ChangeGameFavoriteStatus {

    override suspend fun invoke(
        gameInfo: GameInfo,
        newFavoriteStatus: Boolean
    ): FavoriteStatusResult {
        val success = if (newFavoriteStatus) {
            favoritesRepository.addGameToFavorites(gameInfo.id)
        } else {
            favoritesRepository.removeGameFromFavorites(gameInfo.id)
        }
        return if (success) {
            FavoriteStatusResult.Success(gameInfo.copy(isFavorite = newFavoriteStatus))
        } else {
            FavoriteStatusResult.Failure
        }
    }
}