package bme.aut.sza.honfoglalo.feature.lobby

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.domain.usecases.QuizQuestUseCases
import bme.aut.sza.honfoglalo.ui.model.PlayerUI
import bme.aut.sza.honfoglalo.ui.model.toUiText
import bme.aut.sza.honfoglalo.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import bme.aut.sza.honfoglalo.domain.model.asPlayerUI
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val quizQuestUseCases: QuizQuestUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(PlayerListState())
    val state = _state.asStateFlow()

    private val _gameUiEvent = Channel<UiEvent>()
    val gameUiEvent = _gameUiEvent.receiveAsFlow()

    private val _leaveUiEvent = Channel<UiEvent>()
    val leaveUiEvent = _leaveUiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            quizQuestUseCases.lobbyWaitingUseCase().collect { result ->
                result.onSuccess { (state, players) ->
                    _state.update {
                        it.copy(players = players.map { it.asPlayerUI() })
                    }
                    when (state) {
                        GameStates.CHOOSING_QUESTION -> _gameUiEvent.send(UiEvent.GameStart)
                        else -> { }
                    }
                }.onFailure { e ->
                    _gameUiEvent.send(UiEvent.Failure(e.toUiText()))
                }
            }
        }
    }

    fun onEvent(event: LeaveGameEvent) {
        when (event) {
            LeaveGameEvent.leaveGame -> onLeaveGame()
        }
    }

    private fun onLeaveGame() {
        viewModelScope.launch {
            val result = quizQuestUseCases.leaveGameUseCase()
            if (result.isSuccess) {
                _leaveUiEvent.send(UiEvent.Success)
            } else {
                _leaveUiEvent.send(UiEvent.Failure(result.exceptionOrNull()?.toUiText()!!))
            }
        }
    }
}

data class PlayerListState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val players: List<PlayerUI> = emptyList()
)

sealed class LeaveGameEvent {
    data object leaveGame: LeaveGameEvent()
}