package bme.aut.sza.honfoglalo.feature.lobby

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bme.aut.sza.honfoglalo.data.entities.GameEvents
import bme.aut.sza.honfoglalo.data.util.SocketHandler
import bme.aut.sza.honfoglalo.ui.model.PlayerUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(
    private val socketHandler: SocketHandler,
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerListState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            val socket = socketHandler.getSocket()
            var playerList = listOf<PlayerUI>()
            socket.on(GameEvents.GAME_UPDATED.Name) { args ->
                Log.d("xdd", "kaga")
                val game = JSONObject(args[0].toString()).getJSONObject("game")
                val players = game.getJSONArray("players")

                playerList = (0 until players.length()).map { index ->
                    val playerJson = players.getJSONObject(index)
                    PlayerUI(
                        name = playerJson.getString("name"),
                        score = 0,
                        color = Color(android.graphics.Color.parseColor(playerJson.getString("color")))
                    )
                }
                Log.d("xdd", playerList.toString())
                _state.update {
                    it.copy(
                        players = playerList
                    )
                }
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