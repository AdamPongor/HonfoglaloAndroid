package bme.aut.sza.honfoglalo.domain.model

import bme.aut.sza.honfoglalo.data.entities.GameDataEntity
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.Question

data class GameData(
    val state: GameStates,
    val playerList: List<Player>,
    val question: Question?,
    val myTurn: Boolean?,
)

fun GameDataEntity.asGameData(): GameData = GameData(
    state = state,
    playerList = playerEntity.map { it.asPlayer() },
    question = question,
    myTurn = myTurn
)
