package bme.aut.sza.honfoglalo.ui.model

import bme.aut.sza.honfoglalo.domain.model.JoinData

data class JoinRoomUi(
    val username: String = "",
    val roomCode: String = ""
)


fun JoinRoomUi.asJoinData(): JoinData = JoinData(
    roomCode = roomCode,
    username = username
)