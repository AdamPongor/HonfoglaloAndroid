package bme.aut.sza.honfoglalo.feature.game

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import bme.aut.sza.honfoglalo.data.entities.County
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.ui.common.WaitHourglass
import bme.aut.sza.honfoglalo.ui.map.PlayerInfo
import bme.aut.sza.honfoglalo.ui.map.RoundCounter
import bme.aut.sza.honfoglalo.ui.map.GameMap
import bme.aut.sza.honfoglalo.ui.questions.answerpicking.AnswerPickingQuestion
import bme.aut.sza.honfoglalo.ui.util.GameWaitingTypes
import bme.aut.sza.honfoglalo.ui.util.loadGeoJson
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    viewModel: GameViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        GameMap(
            counties = state.territories,
            onCountyClick = { territory ->
                Log.d("territory: ", territory)
                scope.launch {
                    viewModel.onEvent(GameEvents.selectTerritory, 0, territory)
                }
            },
            scale = 0.9F,
        )

        @Composable fun info() = when (state.waitingTypes) {
            GameWaitingTypes.WAITING_FOR_OTHERS -> {
                WaitHourglass("Waiting for others!")
            }

            GameWaitingTypes.WAITING_FOR_HOST -> {
                WaitHourglass("Waiting for host!")
            }

            GameWaitingTypes.WAITING_FOR_ME -> {
                Text(text = "Select a territory!")
            }

            GameWaitingTypes.NONE -> {}
        }

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
                LazyRow(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    items(state.players.size) { i ->
                        PlayerInfo(player = state.players[i])
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                //RoundCounter(state.totalRounds, state.currentRound)
                info()
            }
        } else {
            Row(
                modifier =
                Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                LazyColumn(
                    modifier =
                    Modifier
                        .padding(start = 16.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {

                }
                info()
                //RoundCounter(totalRounds = state.totalRounds, currentRound = state.currentRound)
            }
        }
    }
}
