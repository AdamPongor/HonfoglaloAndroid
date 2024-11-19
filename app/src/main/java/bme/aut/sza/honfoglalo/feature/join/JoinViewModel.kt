package bme.aut.sza.honfoglalo.feature.join

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bme.aut.sza.honfoglalo.data.GameEvents
import bme.aut.sza.honfoglalo.data.GameStates
import bme.aut.sza.honfoglalo.data.datasource.PreferencesImpl
import bme.aut.sza.honfoglalo.data.util.SocketHandler
import bme.aut.sza.honfoglalo.ui.model.JoinRoomUi
import bme.aut.sza.honfoglalo.ui.model.toUiText
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
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val prefs: PreferencesImpl,
    private val socketHandler: SocketHandler,
) : ViewModel() {
    private val _state = MutableStateFlow(JoinGameState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    joinData = it.joinData.copy(username = getUsername())
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
           joinGame()
           saveUsername(_state.value.joinData.username)
        }
    }


    suspend fun saveUsername(name: String) {
        Log.d("Username: ", name)
        prefs.setPreference(name = name, key = prefs.USERNAME)
    }

    suspend fun getUsername(): String {
        val username =
         prefs.getPreference(key = prefs.USERNAME)
            .stateIn(CoroutineScope(Dispatchers.IO)).value ?: ""
        Log.d("Username: ", username)
        return username
    }

    private suspend fun joinGame(){
        val ip = prefs.getPreference(prefs.serverIP)
            .stateIn(CoroutineScope(Dispatchers.IO)).value ?: "10.0.2.2"
        socketHandler.setSocket(ip)
        socketHandler.establishConnection()
        val socket = socketHandler.getSocket()
        socket.emit(GameEvents.JOIN_GAME.Name,  JSONObject().apply {
                put("code", state.value.joinData.roomCode)
                put("name", state.value.joinData.username)
            }
        )
        socket.once(GameEvents.GAME_UPDATED.Name) { args ->

            Log.d("xdd", args[0].toString())
            val game = JSONObject(args[0].toString()).getJSONObject("game")
            val gameState = game.getString("state")
            if(gameState == GameStates.LOBBY.Name){
                Log.d("xdd", "Navigate here")
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Success) // Emit navigation event
                }
            }
            else if(gameState == GameStates.INCORRECT_ROOM.Name){
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Failure(Error("Failed to join game").toUiText())) // Emit navigation event
                }
            }
            else {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Failure(Error("Game already started").toUiText())) // Emit navigation event
                }
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