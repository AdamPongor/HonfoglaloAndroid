package bme.aut.sza.honfoglalo.feature.game

import android.app.GameState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.Question
import bme.aut.sza.honfoglalo.domain.model.asPlayerUI
import bme.aut.sza.honfoglalo.domain.usecases.QuizQuestUseCases
import bme.aut.sza.honfoglalo.ui.model.PlayerUI
import bme.aut.sza.honfoglalo.ui.util.GameWaitingTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val questUseCases: QuizQuestUseCases
): ViewModel() {
    private val _state = MutableStateFlow(GameScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            questUseCases.handleGameUseCase().collect { result ->
                result.onSuccess { gameState ->
                    _state.update {
                        it.copy(
                            players = gameState.playerList.map { it.asPlayerUI() },
                            currentRound = _state.value.currentRound + 1
                        )
                    }

                    when (gameState.state) {
                        GameStates.CHOOSING_QUESTION -> {
                            _state.update {
                                it.copy(
                                    waitingTypes = GameWaitingTypes.WAITING_FOR_HOST,
                                    gameStates = GameStates.CHOOSING_QUESTION,
                                    hasAnswered = false,
                                )
                            }
                        }
                        GameStates.ANSWERING_QUESTION -> {
                            _state.update {
                                it.copy(
                                    question = gameState.question,
                                    gameStates = GameStates.ANSWERING_QUESTION
                                )
                            }
                        }
                        GameStates.TERRITORY_SELECTION -> {
                            _state.update {
                                it.copy(
                                    gameStates = GameStates.TERRITORY_SELECTION,
                                    waitingTypes = GameWaitingTypes.WAITING_FOR_OTHERS,
                                    hasAnswered = false,
                                )
                            }
                        }
                        else -> { }
                    }
                }
            }
        }
    }

    fun onEvent(event: GameEvents, answer: Int?) {
        when (event) {
            GameEvents.answerQuestion -> {
                val response = _state.value.question!!.answers[answer!!]
                answerQuestion(response)
                _state.update {
                    it.copy(
                        gameStates = GameStates.TERRITORY_SELECTION,
                        waitingTypes = GameWaitingTypes.WAITING_FOR_OTHERS,
                        hasAnswered = true
                    )
                }
            }
        }
    }

    private fun answerQuestion(response: String) {
        viewModelScope.launch {
            questUseCases.answerQuestionUseCase(answer = response)
        }
    }
}

data class GameScreenState(
    val gameStates: GameStates = GameStates.CHOOSING_QUESTION,
    val waitingTypes: GameWaitingTypes = GameWaitingTypes.WAITING_FOR_HOST,
    val players: List<PlayerUI> = emptyList(),
    val question: Question? = null,
    val currentRound: Int = 0,
    val totalRound: Int = 10,
    val hasAnswered: Boolean = false,
)

sealed class GameEvents {
    data object answerQuestion: GameEvents()
}