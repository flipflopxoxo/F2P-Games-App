package com.clydelizardo.f2pgames.list.viewmodel

import androidx.lifecycle.*
import com.clydelizardo.f2pgames.list.usecase.ChangeGameFavoriteStatus
import com.clydelizardo.f2pgames.list.usecase.FavoriteStatusResult
import com.clydelizardo.f2pgames.list.usecase.GetFreeGames
import com.clydelizardo.f2pgames.list.usecase.GetFreeGamesResult
import com.clydelizardo.f2pgames.model.GameInfo
import com.clydelizardo.f2pgames.util.Status
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
    private val refreshCounter = MutableLiveData(0)
    private val resultFlow = refreshCounter.switchMap {
        liveData {
            emit(Status.Loading())
            when (val freeGames = getFreeGames()) {
                GetFreeGamesResult.Failure -> emit(Status.Failure())
                is GetFreeGamesResult.Success -> emit(Status.Success(freeGames.list))
            }
        }
    }

    val isLoading = resultFlow.map { it is Status.Loading<*> }
    val list = MediatorLiveData<List<GameInfo>>().apply {
        addSource(resultFlow) {
            if (it is Status.Success) {
                value = it.content
            }
        }
    }
    val failures = MediatorLiveData<Status.Failure<Any>>().apply {
        addSource(resultFlow) {
            if (it is Status.Failure) {
                value = it
            }
        }
    }
    val favoriteUpdateResult = MutableLiveData<FavoriteStatusResult>()

    fun refresh() {
        refreshCounter.value = refreshCounter.value?.plus(1)
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