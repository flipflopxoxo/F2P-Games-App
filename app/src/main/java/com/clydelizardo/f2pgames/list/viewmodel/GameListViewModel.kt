package com.clydelizardo.f2pgames.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clydelizardo.f2pgames.list.usecase.ChangeGameFavoriteStatus
import com.clydelizardo.f2pgames.list.usecase.FavoriteStatusResult
import com.clydelizardo.f2pgames.list.usecase.GetFreeGames
import com.clydelizardo.f2pgames.list.usecase.GetFreeGamesResult
import com.clydelizardo.f2pgames.model.GameInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val getFreeGames: GetFreeGames,
    private val changeGameFavoriteStatus: ChangeGameFavoriteStatus
) : ViewModel() {
    val listOfGames: MutableStateFlow<List<GameInfo>> = MutableStateFlow(emptyList())

    private val _state = MutableStateFlow<GameListState>(GameListState.Loading)
    val state = _state.asStateFlow()

    init {
        doInitialLoad()
    }

    private fun doInitialLoad() {
        viewModelScope.launch {
            val freeGames = getFreeGames()
            _state.value = when (freeGames) {
                is GetFreeGamesResult.Success -> GameListState.Success(freeGames.list)
                else -> GameListState.Failure
            }
        }
    }

    fun refresh(): Boolean {
        return when {
            _state.compareAndTransform({ it is GameListState.Success }) {
                GameListState.Refreshing((it as GameListState.Success).gameList)
            } -> {
                doRefresh()
                true
            }
            _state.compareAndTransform({ it is GameListState.Failure }) {
                GameListState.Loading
            } -> {
                doInitialLoad()
                true
            }
            else -> {
                false
            }
        }
    }

    private fun doRefresh() {
        viewModelScope.launch {
            val oldList = (_state.value as GameListState.Refreshing).gameList
            val freeGames = getFreeGames()
            _state.value = when (freeGames) {
                is GetFreeGamesResult.Success -> GameListState.Success(freeGames.list)
                else -> GameListState.FailedRefresh(oldList)
            }
        }
    }

    fun removeFailedState() {
        _state.compareAndTransform({
            it is GameListState.FailedRefresh || it is GameListState.FailedUpdate
        }) {
            val oldList = when (it) {
                is GameListState.FailedRefresh -> it.gameList
                is GameListState.FailedUpdate -> it.gameList
                else -> throw IllegalStateException()
            }
            GameListState.Success(oldList)
        }
    }

    fun toggleFavoriteState(gameInfo: GameInfo) {
        if (_state.compareAndTransform({ it is GameListState.Success }) {
                GameListState.Updating((it as GameListState.Success).gameList)
            }) {
            viewModelScope.launch {
                val oldList = (_state.value as GameListState.Updating).gameList
                val favoriteStatusResult = changeGameFavoriteStatus(gameInfo, !gameInfo.isFavorite)
                _state.value = when (favoriteStatusResult) {
                    is FavoriteStatusResult.Success -> {
                        val updatedGameInfo = favoriteStatusResult.updatedGameInfo
                        GameListState.Success(
                            oldList.map {
                                if (it.id == updatedGameInfo.id) {
                                    updatedGameInfo
                                } else {
                                    it
                                }
                            }
                        )
                    }
                    FavoriteStatusResult.Failure -> {
                        GameListState.FailedUpdate(oldList)
                    }
                }
            }
        }
    }
}

sealed class GameListState(
    val isRefreshing: Boolean = false,
    val gameList: List<GameInfo> = emptyList()
) {
    object Loading : GameListState(isRefreshing = true)
    object Failure : GameListState()
    data class Success(val list: List<GameInfo>) : GameListState(gameList = list)
    data class Refreshing(val list: List<GameInfo>) : GameListState(isRefreshing = true, gameList = list)
    data class Updating(val list: List<GameInfo>) : GameListState(isRefreshing = true, gameList = list)
    data class FailedRefresh(val list: List<GameInfo>) : GameListState(gameList = list)
    data class FailedUpdate(val list: List<GameInfo>) : GameListState(gameList = list)
}

fun <T> MutableStateFlow<T>.compareAndTransform(
    predicate: (T) -> Boolean,
    transform: (T) -> T
): Boolean {
    return synchronized(this) {
        if (predicate(this.value)) {
            value = transform(this.value)
            true
        } else {
            false
        }
    }
}