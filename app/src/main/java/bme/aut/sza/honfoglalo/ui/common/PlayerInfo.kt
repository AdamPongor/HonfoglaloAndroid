package bme.aut.sza.honfoglalo.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bme.aut.sza.honfoglalo.data.Player
import bme.aut.sza.honfoglalo.ui.theme.FlatCornerShape

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PlayerInfo(player: Player) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(4.dp),
    ) {
        Box(
            modifier =
            Modifier
                .width(130.dp)
                .height(100.dp)
                .padding(3.dp)
                .border(width = 3.dp, color = Color.Black, shape = FlatCornerShape)
                .background(player.color, FlatCornerShape),
            contentAlignment = Alignment.CenterStart,
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedText(
                    text = player.name,
                    modifier =
                    Modifier
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fillColor = Color.White,
                    outlineColor = Color.Black,
                    outlineDrawStyle = Stroke(width = 7f),
                )
                OutlinedText(
                    text = player.score.toString(),
                    modifier =
                    Modifier
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fillColor = Color.White,
                    outlineColor = Color.Black,
                    outlineDrawStyle = Stroke(width = 7f),
                )
            }
        }
    }
}

@Preview
@Composable
fun PlayerInfoPreview() {
    val player = Player("Lajos", 6900, Color.Red)
    PlayerInfo(player)
}
