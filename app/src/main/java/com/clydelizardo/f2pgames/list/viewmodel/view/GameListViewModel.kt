package com.clydelizardo.f2pgames.list.viewmodel.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clydelizardo.f2pgames.list.usecase.GetFreeGames
import com.clydelizardo.f2pgames.list.usecase.GetFreeGamesResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameListViewModel(getFreeGames: GetFreeGames) : ViewModel() {
    val listOfGames: MutableStateFlow<List<GameInfo>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            val getFreeGamesResult = withContext(Dispatchers.IO) {
                getFreeGames()
            }
            when (getFreeGamesResult) {
                is GetFreeGamesResult.Success -> {
                    listOfGames.value = getFreeGamesResult.list
                }
                GetFreeGamesResult.Failure -> {
                    listOfGames.value = emptyList()
                }
            }
        }
        onCleared()
    }
}