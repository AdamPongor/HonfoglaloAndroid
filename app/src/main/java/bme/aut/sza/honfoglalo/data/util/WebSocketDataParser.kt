package bme.aut.sza.honfoglalo.data.util

import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.ui.graphics.Color
import bme.aut.sza.honfoglalo.data.entities.AnswerEntity
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.PlayerEntity
import bme.aut.sza.honfoglalo.data.entities.QuestionEntity
import bme.aut.sza.honfoglalo.data.entities.TerritorySelection
import bme.aut.sza.honfoglalo.data.entities.TerritorySelections
import org.json.JSONObject

object WebSocketDataParser {
    fun parseGameState(args: Array<Any>): GameStates? {
        val response = JSONObject(args[0].toString())
        Log.d("Response: ", response.toString())
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

    fun myPlayer(args: Array<Any>): String {
        val response = JSONObject(args[0].toString())
        val myPlayer = response.getJSONObject("player")
        val id = myPlayer.getString("id")
        return id
    }

    fun parseQuestion(args: Array<Any>): QuestionEntity {
        val response = JSONObject(args[0].toString())
        val questionState = response.getJSONObject("questionState")
        val questionObject = questionState.getJSONObject("question")
        val question = questionObject.getString("question")
        val answer = questionObject.getString("answer")
        return QuestionEntity(
            question = question,
            answers = listOf(AnswerEntity(answer))
        )
    }

    fun parseTerritorySelection(args: Array<Any>): TerritorySelections {
        val response = JSONObject(args[0].toString())
        val territorySelection = response.getJSONArray("territorySelection")

        val selections = mutableListOf<TerritorySelection>()
        for (i in 0 until territorySelection.length()) {
            val item = territorySelection.getJSONObject(i)
            val id = item.getString("id")
            val selectionsCount = item.getInt("selections")
            selections.add(TerritorySelection(id, selectionsCount))
        }

        return TerritorySelections(selections)
    }
}