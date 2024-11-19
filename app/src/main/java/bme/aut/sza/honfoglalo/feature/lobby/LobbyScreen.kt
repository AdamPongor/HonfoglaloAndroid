package bme.aut.sza.honfoglalo.feature.lobby

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bme.aut.sza.honfoglalo.R
import bme.aut.sza.honfoglalo.ui.common.PlayerListUI
import bme.aut.sza.honfoglalo.ui.theme.FlatCornerShape
import bme.aut.sza.honfoglalo.ui.theme.Purple40

@Composable
fun LobbyScreen(
    viewModel: LobbyViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()//.background(color = Tan)
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

        LazyColumn {
            items(state.players.size) { i ->
                PlayerListUI(color = state.players[i].color, name = state.players[i].name)
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
                /*TODO: Leave Lobby connection*/
            }
        ) {
            Text(text = "Leave Lobby")
        }
    }
}

@Preview
@Composable
private fun LobbyPreview() {
    LobbyScreen()
}