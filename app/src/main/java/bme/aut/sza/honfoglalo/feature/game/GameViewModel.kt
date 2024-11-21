package bme.aut.sza.honfoglalo.feature.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.domain.model.asPlayer
import bme.aut.sza.honfoglalo.domain.model.asPlayerUI
import bme.aut.sza.honfoglalo.domain.usecases.QuizQuestUseCases
import bme.aut.sza.honfoglalo.ui.model.PlayerUI
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
    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            questUseCases.handleGameUseCase().collect { result ->
                result.onSuccess { gameState ->
                    _state.update {
                        it.copy(
                            players = gameState.playerList.map { it.asPlayerUI() }
                        )
                        it.copy(
                            currentRound = _state.value.currentRound + 1
                        )
                    }

                    when (gameState.state) {
                        GameStates.CHOOSING_QUESTION -> {
                            
                        }
                        GameStates.ANSWERING_QUESTION -> {

                        }
                        GameStates.TERRITORY_SELECTION -> {

                        }
                        else -> { }
                    }
                }
            }
        }
    }

}

data class GameState(
    val players: List<PlayerUI> = emptyList(),
    val currentRound: Int = 0,
    val totalRound: Int = 10,
)