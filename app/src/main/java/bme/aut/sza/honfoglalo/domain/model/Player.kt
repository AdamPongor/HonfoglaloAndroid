package bme.aut.sza.honfoglalo.domain.model

import androidx.compose.ui.graphics.Color
import bme.aut.sza.honfoglalo.data.entities.PlayerEntity
import bme.aut.sza.honfoglalo.ui.model.PlayerUI

data class Player(
    val name: String,
    val score: Int,
    val color: Color
)

fun PlayerEntity.asPlayer(): Player = Player(
    name = name,
    score = score,
    color = color
)

fun Player.asPlayerUI(): PlayerUI = PlayerUI(
    name = name,
    score = score,
    color = color
)