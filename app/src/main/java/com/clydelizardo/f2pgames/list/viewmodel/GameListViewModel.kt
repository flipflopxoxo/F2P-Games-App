package com.clydelizardo.f2pgames.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clydelizardo.f2pgames.list.usecase.GetFreeGames
import com.clydelizardo.f2pgames.list.usecase.GetFreeGamesResult
import com.clydelizardo.f2pgames.list.viewmodel.view.GameInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GameListViewModel(private val getFreeGames: GetFreeGames) : ViewModel() {
    val listOfGames: MutableStateFlow<List<GameInfo>> = MutableStateFlow(emptyList())

    val _dataLoading = MutableStateFlow(false)

    fun start() {
        if (_dataLoading.compareAndSet(expect = false, update = true)) {
            viewModelScope.launch {
                try {
                    val freeGames = getFreeGames()
                    when (freeGames) {
                        is GetFreeGamesResult.Success -> listOfGames.value = freeGames.list
                        else -> listOfGames.value = emptyList()
                    }
                } finally {
                    _dataLoading.value = false
                }
            }
        }
    }
}