package bme.aut.sza.honfoglalo.domain.model

import bme.aut.sza.honfoglalo.data.entities.JoinGameEntity

data class JoinData(
    val username: String,
    val roomCode: String,
)

fun JoinGameEntity.asJoinData(): JoinData = JoinData(
    username = username,
    roomCode = gameCode
)

fun JoinData.asJoinGameEntity(): JoinGameEntity = JoinGameEntity(
    username = username,
    gameCode = roomCode
)
