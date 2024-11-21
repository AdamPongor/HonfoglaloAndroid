package bme.aut.sza.honfoglalo.feature.game

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bme.aut.sza.honfoglalo.data.entities.Category
import bme.aut.sza.honfoglalo.data.entities.County
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.Question
import bme.aut.sza.honfoglalo.feature.lobby.LeaveGameEvent
import bme.aut.sza.honfoglalo.ui.model.PlayerUI
import bme.aut.sza.honfoglalo.ui.map.PlayerInfo
import bme.aut.sza.honfoglalo.ui.map.RoundCounter
import bme.aut.sza.honfoglalo.ui.map.GameMap
import bme.aut.sza.honfoglalo.ui.questions.answerpicking.AnswerPickingQuestion
import bme.aut.sza.honfoglalo.ui.theme.FlatCornerShape
import bme.aut.sza.honfoglalo.ui.util.UiEvent
import bme.aut.sza.honfoglalo.ui.util.loadGeoJson
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    val regions = remember { mutableStateOf<List<County>>(emptyList()) }
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val state by viewModel.state.collectAsStateWithLifecycle()

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

        when (state.gameStates == GameStates.ANSWERING_QUESTION && !state.hasAnswered) {
            true -> {
                AnswerPickingQuestion(
                    question = state.question!!,
                    onAnswerSelected = { index ->
                        scope.launch {
                            viewModel.onEvent(GameEvents.answerQuestion, index)
                        }
                    }
                )
            }
            false -> { }
        }


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
                    state.players.forEach { player ->
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
                    state.players.forEach { player ->
                        PlayerInfo(player = player)
                    }
                }

                RoundCounter(totalRounds = state.totalRound, currentRound = state.currentRound)
            }
        }
    }
}
