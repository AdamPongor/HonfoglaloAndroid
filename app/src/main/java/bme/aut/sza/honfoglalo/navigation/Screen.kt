package bme.aut.sza.honfoglalo.navigation

sealed class Screen(val route: String) {
    data object Menu : Screen("menu")

    data object Lobby : Screen("lobby")

    data object Game : Screen("game")
}
