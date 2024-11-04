package bme.aut.sza.honfoglalo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import bme.aut.sza.honfoglalo.data.Player
import bme.aut.sza.honfoglalo.feature.join.JoinScreen
import bme.aut.sza.honfoglalo.ui.theme.HonfoglaloTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val players =
            listOf(
                Player("Alice", 50, Color.Red),
                Player("Bob", 50, Color.Blue),
                Player("Dave", 50, Color.Green),
            )
        setContent {
            HonfoglaloTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    JoinScreen()
                    //GameScreen(players, 10, 4)
                }
            }
        }
    }
}