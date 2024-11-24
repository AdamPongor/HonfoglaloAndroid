package bme.aut.sza.honfoglalo.feature.game

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bme.aut.sza.honfoglalo.data.entities.County
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.domain.model.Territory
import bme.aut.sza.honfoglalo.domain.model.asPlayerUI
import bme.aut.sza.honfoglalo.domain.usecases.QuizQuestUseCases
import bme.aut.sza.honfoglalo.ui.model.AnswerUi
import bme.aut.sza.honfoglalo.ui.model.PlayerUI
import bme.aut.sza.honfoglalo.ui.model.QuestionUi
import bme.aut.sza.honfoglalo.ui.model.asAnswer
import bme.aut.sza.honfoglalo.ui.model.asQuestionUi
import bme.aut.sza.honfoglalo.ui.util.GameWaitingTypes
import bme.aut.sza.honfoglalo.ui.util.loadGeoJson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val questUseCases: QuizQuestUseCases,
    @ApplicationContext val context: Context
): ViewModel() {
    private val _state = MutableStateFlow(GameScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {

            _state.update {
                it.copy(
                    territories = loadGeoJson(context)
                )
            }

            questUseCases.handleGameUseCase().collect { result ->
                result.onSuccess { gameState ->

                    _state.update {
                        for (i in gameState.territories){
                            val newCounty = _state.value.territories.find { it.name == i.territory }
                            newCounty?.color = i.color!!
                        }

                        it.copy(
                            players = gameState.playerList.map { it.asPlayerUI() },
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
                                    question = gameState.question!!.asQuestionUi(),
                                    gameStates = GameStates.ANSWERING_QUESTION,
                                    waitingTypes = if(state.value.hasAnswered) GameWaitingTypes.WAITING_FOR_OTHERS else GameWaitingTypes.NONE
                                )
                            }
                        }
                        GameStates.TERRITORY_SELECTION -> {
                            _state.update {
                                it.copy(
                                    gameStates = GameStates.TERRITORY_SELECTION,
                                    waitingTypes = if(gameState.myTurn!!) GameWaitingTypes.WAITING_FOR_ME else GameWaitingTypes.WAITING_FOR_OTHERS
                                )
                            }
                        }
                        GameStates.END -> {
                            _state.update {
                                it.copy(
                                    gameStates = GameStates.END,
                                    waitingTypes = GameWaitingTypes.NONE
                                )
                            }
                        }
                        else -> { }
                    }
                }.onFailure { error ->
                    Log.d("Error: ", error.toString())
                }
            }
        }
    }

    fun onEvent(event: GameEvents, answer: String? = "", territory: String? = "") {
        when (event) {
            GameEvents.answerQuestion -> {
                answerQuestion(
                    AnswerUi(answer!!)
                )
                _state.update {
                    it.copy(
                        //gameStates = GameStates.TERRITORY_SELECTION,
                        hasAnswered = true
                    )
                }
            }

            GameEvents.selectTerritory -> {
                selectTerritory(Territory(territory!!, null))
                _state.update {
                    it.copy(
                        //gameStates = GameStates.CHOOSING_QUESTION,
                    )
                }
            }
        }
    }

    private fun answerQuestion(answer: AnswerUi) {
        viewModelScope.launch {
            questUseCases.answerQuestionUseCase(answer = answer.asAnswer())
        }
    }

    private fun selectTerritory(territory: Territory) {
        viewModelScope.launch {
            questUseCases.selectTerritoryUseCase(territory = territory)
        }
    }
}

data class GameScreenState(
    val gameStates: GameStates = GameStates.CHOOSING_QUESTION,
    val waitingTypes: GameWaitingTypes = GameWaitingTypes.WAITING_FOR_HOST,
    val players: List<PlayerUI> = emptyList(),
    val territories: List<County> = emptyList(),
    val question: QuestionUi? = null,
    val hasAnswered: Boolean = false,
)

sealed class GameEvents {
    data object answerQuestion: GameEvents()
    data object selectTerritory: GameEvents()
}