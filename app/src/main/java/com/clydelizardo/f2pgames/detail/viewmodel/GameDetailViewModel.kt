package com.clydelizardo.f2pgames.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clydelizardo.f2pgames.detail.usecase.GetGameDetail
import com.clydelizardo.f2pgames.detail.usecase.GetGameDetailResult
import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.model.GameInfo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GameDetailViewModel(private val getGameDetail: GetGameDetail): ViewModel() {
    private val _detailFlow = MutableStateFlow<DetailState>(DetailState.Loading)
    val detail = _detailFlow.asStateFlow()

    fun setGame(gameInfo: GameInfo) {
        viewModelScope.launch {
            _detailFlow.value = DetailState.Loading
            val detailResult = getGameDetail(gameInfo)
            _detailFlow.value = when (detailResult) {
                is GetGameDetailResult.Success -> {
                    DetailState.Success(detailResult.value)
                }
                GetGameDetailResult.Failure -> {
                    DetailState.Failure
                }
            }
        }
    }
}

sealed class DetailState {
    class Success(val detail: GameDetail): DetailState()
    object Loading: DetailState()
    object Failure: DetailState()
}