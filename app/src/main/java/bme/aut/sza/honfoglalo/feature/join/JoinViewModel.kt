package bme.aut.sza.honfoglalo.feature.join

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bme.aut.sza.honfoglalo.domain.usecases.QuizQuestUseCases
import bme.aut.sza.honfoglalo.ui.model.JoinRoomUi
import bme.aut.sza.honfoglalo.ui.model.asJoinData
import bme.aut.sza.honfoglalo.ui.model.toUiText
import bme.aut.sza.honfoglalo.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val quizQuestUseCases: QuizQuestUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(JoinGameState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            val username = quizQuestUseCases.loadUsernameUseCase().getOrDefault("")
            _state.update {
                it.copy(
                    joinData = it.joinData.copy(username = username)
                )
            }
        }
    }

    fun onEvent (event: JoinGameEvent) {
        when (event) {
            is JoinGameEvent.ChangeUsername -> {
                val newValue = event.text
                _state.update {
                    it.copy(
                        joinData = it.joinData.copy(username = newValue)
                    )
                }
            }
            is JoinGameEvent.ChangeRoomCode -> {
                val newValue = event.text
                _state.update {
                    it.copy(
                        joinData = it.joinData.copy(roomCode = newValue)
                    )
                }
            }
            JoinGameEvent.joinGame -> {
                onJoin()
            }
        }
    }

    private fun onJoin() {
        viewModelScope.launch {
            val result = quizQuestUseCases.joinGame(state.value.joinData.asJoinData())
            if (result.isSuccess) {
                _uiEvent.send(UiEvent.Success)
            } else {
                _uiEvent.send(UiEvent.Failure(result.exceptionOrNull()?.toUiText()!!))
            }
        }
    }
}

data class JoinGameState(
    val joinData: JoinRoomUi = JoinRoomUi()
)

sealed class JoinGameEvent {
    data class ChangeUsername(val text: String): JoinGameEvent()
    data class ChangeRoomCode(val text: String): JoinGameEvent()
    data object joinGame: JoinGameEvent()
}