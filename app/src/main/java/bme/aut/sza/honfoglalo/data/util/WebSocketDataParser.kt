package bme.aut.sza.honfoglalo.data.util

import android.graphics.Color.parseColor
import androidx.compose.ui.graphics.Color
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.PlayerEntity
import org.json.JSONObject

object WebSocketDataParser {
    fun parseGameState(args: Array<Any>): GameStates? {
        val response = JSONObject(args[0].toString())
        val game = response.getJSONObject("game")
        val gameState = game.getString("state")
        val state = GameStates.entries.find { it.Name == gameState }
        return state
    }

    fun parsePlayers(args: Array<Any>): List<PlayerEntity> {
        val playerList: List<PlayerEntity>
        val response = JSONObject(args[0].toString())
        val game = response.getJSONObject("game")
        val players = game.getJSONArray("players")

        playerList = (0 until players.length()).map { index ->
            val playerJson = players.getJSONObject(index)
            PlayerEntity(
                name = playerJson.getString("name"),
                score = 0,
                color = Color(parseColor(playerJson.getString("color")))
            )
        }
        return playerList
    }
}