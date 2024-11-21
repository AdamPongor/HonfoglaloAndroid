package bme.aut.sza.honfoglalo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bme.aut.sza.honfoglalo.feature.game.GameScreen
import bme.aut.sza.honfoglalo.feature.join.JoinScreen
import bme.aut.sza.honfoglalo.feature.lobby.LobbyScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Join.route,
    ) {
        composable(Screen.Join.route) {
            JoinScreen(
                onJoinGame = {
                    navController.navigate(Screen.Lobby.route)
                }
            )
        }
        composable(Screen.Lobby.route) {
            LobbyScreen(
                onLeaveGame = {
                    navController.popBackStack()
                },
                onGameStart = {
                    navController.navigate(Screen.Game.route)
                }
            )
        }
        composable(Screen.Game.route) {
            GameScreen()
        }
    }
}