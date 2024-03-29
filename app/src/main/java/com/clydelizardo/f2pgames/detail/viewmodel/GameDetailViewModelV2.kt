package com.clydelizardo.f2pgames.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.clydelizardo.f2pgames.detail.usecase.GetGameDetail
import com.clydelizardo.f2pgames.detail.usecase.GetGameDetailResult
import com.clydelizardo.f2pgames.detail.view.GameDetailFragmentArgs
import com.clydelizardo.f2pgames.model.GameDetail
import com.clydelizardo.f2pgames.util.filterIsInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModelV2 @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getGameDetail: GetGameDetail
) : ViewModel() {
    val gameInfo = GameDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).gameInfo

    private val detailResult by lazy {
        liveData {
            emit(DetailState.Loading)
            emit(
                when (val gameDetail = getGameDetail(gameInfo)) {
                    is GetGameDetailResult.Success -> {
                        DetailState.Success(gameDetail.value)
                    }
                    GetGameDetailResult.Failure -> {
                        DetailState.Failure
                    }
                }
            )
        }
    }
    val detail by lazy {
        detailResult.filterIsInstance<DetailState.Success>()
            .map(DetailState.Success::detail)
    }

    val isLoading by lazy {
        detailResult.map {
            it is DetailState.Loading
        }
    }

    sealed class DetailState {
        class Success(val detail: GameDetail) : DetailState()
        object Loading : DetailState()
        object Failure : DetailState()
    }
}