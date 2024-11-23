package bme.aut.sza.honfoglalo.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import bme.aut.sza.honfoglalo.feature.game.GameScreen
import bme.aut.sza.honfoglalo.ui.model.PlayerUI
import bme.aut.sza.honfoglalo.ui.theme.FlatCornerShape

@Composable
fun LeaderBoard(
    players: List<PlayerUI>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
) {
    Popup (
        onDismissRequest = onDismissRequest,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Final results", fontSize = 50.sp, fontWeight = FontWeight.Bold)
                LazyColumn(modifier = modifier) {
                    val sortedPlayers = players.sortedByDescending { it.score }

                    items(sortedPlayers) { player ->
                        PlayerListUI(
                            color = player.color,
                            name = player.name,
                            score = player.score
                        )
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
                        onClick()
                    }
                ) {
                    Text("Új játék")
                }
            }
        }
    }
}

@Preview
@Composable
fun LeaderBoardPreview() {
    LeaderBoard(
        players = listOf(
            PlayerUI(
                name = "Varesz",
                score = 11,
                color = Color.Black
            ),
            PlayerUI(
                name = "xdd",
                score = 2,
                color = Color.Red
            )
        ),
        onClick = { }
    )
}