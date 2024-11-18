package bme.aut.sza.honfoglalo.feature.join

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bme.aut.sza.honfoglalo.data.datasource.PreferencesImpl
import bme.aut.sza.honfoglalo.ui.model.JoinRoomUi
import bme.aut.sza.honfoglalo.ui.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val prefs: PreferencesImpl
) : ViewModel() {
    private val _state = MutableStateFlow(JoinGameState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {

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
            JoinGameEvent.joinGame -> TODO()
        }
    }

    private fun onJoin() {
        viewModelScope.launch {
            // TODO - join through WS and save preferences
        }
    }


    suspend fun saveUsername(name: String) {
        prefs.setPreference(name = name, key = prefs.USERNAME)
    }

    suspend fun getUsername(): String {
        return prefs.getPreference(key = prefs.USERNAME)
            .stateIn(CoroutineScope(Dispatchers.IO)).value ?: ""
    }

    fun ConnectWebSocket() {
        /*TODO*/
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
