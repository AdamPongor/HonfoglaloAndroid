package bme.aut.sza.honfoglalo.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import bme.aut.sza.honfoglalo.data.County
import bme.aut.sza.honfoglalo.data.Player
import bme.aut.sza.honfoglalo.ui.common.PlayerInfo
import bme.aut.sza.honfoglalo.ui.common.RoundCounter
import bme.aut.sza.honfoglalo.ui.map.GameMap
import bme.aut.sza.honfoglalo.util.loadGeoJson

@Composable
fun GameScreen(
    players: List<Player>,
    totalRounds: Int,
    currentRound: Int,
) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    val regions = remember { mutableStateOf<List<County>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        regions.value = loadGeoJson(context)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        GameMap(
            counties = regions.value,
            onCountyClick = { region ->
                regions.value =
                    regions.value.map {
                        if (it == region) it.copy(color = Color.Red) else it
                    }
            },
            scale = 0.65F,
        )

        if (isPortrait) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    players.forEach { player ->
                        PlayerInfo(player = player)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                RoundCounter(10, 4)
            }
        } else {
            Row(
                modifier =
                    Modifier
                        .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier =
                        Modifier
                            .padding(start = 16.dp)
                            .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {
                    players.forEach { player ->
                        PlayerInfo(player = player)
                    }
                }

                RoundCounter(totalRounds = totalRounds, currentRound = currentRound)
            }
        }
    }
}
