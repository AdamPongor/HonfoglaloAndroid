package bme.aut.sza.honfoglalo.feature.join

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bme.aut.sza.honfoglalo.R
import bme.aut.sza.honfoglalo.ui.theme.FlatCornerShape
import bme.aut.sza.honfoglalo.ui.theme.Purple40
import kotlinx.coroutines.launch


@Composable
fun JoinScreen(viewModel: JoinViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()

    //TODO: DELETE LATER
    val hostState = remember { SnackbarHostState() }

    val context = LocalContext.current

//    TODO: Ezt kitalálni hogy legyen
//    LaunchedEffect(key1 = "") {
//        usernameValue = viewModel.getUsername()
//    }

    Scaffold (
        snackbarHost = { SnackbarHost(hostState) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

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

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(shape = FlatCornerShape),
                value = state.joinData.username,
                label = @Composable { Text(text = "Play as:") },
                placeholder = @Composable { Text(text = "Username") },
                onValueChange = { viewModel.onEvent(JoinGameEvent.ChangeUsername(it)) }
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(shape = FlatCornerShape),
                value = state.joinData.roomCode,
                label = @Composable { Text(text = "Room code:") },
                onValueChange = { viewModel.onEvent(JoinGameEvent.ChangeRoomCode(it)) }
            )

            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(75.dp)
                    .padding(10.dp)
                    .clip(shape = FlatCornerShape),
                shape = FlatCornerShape,
                onClick = {
                    viewModel.onEvent(JoinGameEvent.joinGame)
                    /*TODO: Make websocket connection*/
//                    scope.launch {
//                        viewModel.saveUsername(usernameValue)
//                        //TODO: DELETE LATER
//                        snackbarHostState.showSnackbar("Join room ${roomCodeValue} with name ${usernameValue}")
//                    }
                }
            ) {
                Text(text = "Join")
            }
        }
    }
}