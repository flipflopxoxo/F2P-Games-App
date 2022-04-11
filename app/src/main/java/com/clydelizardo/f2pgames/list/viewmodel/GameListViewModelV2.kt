package com.clydelizardo.f2pgames.list.viewmodel

import androidx.lifecycle.*
import com.clydelizardo.f2pgames.list.usecase.ChangeGameFavoriteStatus
import com.clydelizardo.f2pgames.list.usecase.FavoriteStatusResult
import com.clydelizardo.f2pgames.list.usecase.GetFreeGames
import com.clydelizardo.f2pgames.list.usecase.GetFreeGamesResult
import com.clydelizardo.f2pgames.model.GameInfo
import com.clydelizardo.f2pgames.util.Status
import com.clydelizardo.f2pgames.util.filterIsInstance
import com.clydelizardo.f2pgames.util.refreshableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class GameListViewModelV2 @Inject constructor(
    private val getFreeGames: GetFreeGames,
    private val changeGameFavoriteStatus: ChangeGameFavoriteStatus
) : ViewModel() {
    private val resultLiveData = refreshableLiveData {
        emit(Status.Loading())
        when (val freeGames = getFreeGames()) {
            GetFreeGamesResult.Failure -> emit(Status.Failure())
            is GetFreeGamesResult.Success -> emit(Status.Success(freeGames.list))
        }
    }

    val isLoading = resultLiveData.map { it is Status.Loading<*> }
    val list = resultLiveData.filterIsInstance<Status.Success<List<GameInfo>>>()
        .map {
            it.content
        }

    val failures = resultLiveData.filterIsInstance<Status.Failure<Any>>()
    val favoriteUpdateResult = MutableLiveData<FavoriteStatusResult>()

    fun refresh() {
        resultLiveData.refresh()
    }

    fun toggleFavoriteState(gameInfo: GameInfo) {
        if (isLoading.value == true) {
            return
        }
        viewModelScope.launch {
            val favouriteStatusUpdateResult = changeGameFavoriteStatus(gameInfo, !gameInfo.isFavorite)
            favoriteUpdateResult.value = favouriteStatusUpdateResult
            if (favouriteStatusUpdateResult is FavoriteStatusResult.Success) {
                refresh()
            }
        }
    }
}