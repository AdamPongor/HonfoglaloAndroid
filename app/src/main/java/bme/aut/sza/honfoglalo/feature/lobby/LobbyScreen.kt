package bme.aut.sza.honfoglalo.feature.lobby

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bme.aut.sza.honfoglalo.R
import bme.aut.sza.honfoglalo.ui.common.PlayerListUI
import bme.aut.sza.honfoglalo.ui.theme.FlatCornerShape
import bme.aut.sza.honfoglalo.ui.theme.Purple40
import bme.aut.sza.honfoglalo.ui.util.UiEvent
import kotlinx.coroutines.launch

@Composable
fun LobbyScreen(
    onLeaveGame: () -> Unit,
    onGameStart: () -> Unit,
    viewModel: LobbyViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()

    val hostState = remember { SnackbarHostState() }

    val context = LocalContext.current

    LaunchedEffect(key1 = "") {
        viewModel.gameUiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Failure -> {
                    hostState.showSnackbar(uiEvent.message.asString(context))
                }
                UiEvent.GameStart -> onGameStart()
                else -> { }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)//.background(color = Tan)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight(0.3f),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                colorFilter = ColorFilter.tint(
                    color = Purple40
                ),
            )

            Text(text = "Please wait for the host to start the game")

            Spacer(modifier = Modifier.padding(10.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                LazyColumn {
                    items(state.players.size) { i ->
                        PlayerListUI(color = state.players[i].color, name = state.players[i].name)
                    }
                }
            }

            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(75.dp)
                    .padding(10.dp)
                    .clip(shape = FlatCornerShape),
                shape = FlatCornerShape,
                onClick = {
                    viewModel.onEvent(LeaveGameEvent.leaveGame)
                    scope.launch {
                        viewModel.leaveUiEvent.collect { uiEvent ->
                            when (uiEvent) {
                                is UiEvent.Failure -> {
                                    hostState.showSnackbar(uiEvent.message.asString(context))
                                }

                                UiEvent.Success -> onLeaveGame()
                                else -> {}
                            }
                        }
                    }
                }
            ) {
                Text(text = "Leave Lobby")
            }
        }
    }
}