package bme.aut.sza.honfoglalo.feature.lobby

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import bme.aut.sza.honfoglalo.data.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(PlayerListState())
    val state = _state.asStateFlow()

}

data class PlayerListState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isError: Boolean = error != null,
    val players: List<Player> = emptyList()
)