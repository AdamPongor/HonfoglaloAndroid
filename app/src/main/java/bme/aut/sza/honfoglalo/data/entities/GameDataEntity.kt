package bme.aut.sza.honfoglalo.data.entities

import bme.aut.sza.honfoglalo.domain.model.Territory

data class GameDataEntity(
    val state: GameStates,
    val playerEntity: List<PlayerEntity>,
    val question: QuestionEntity?,
    val myTurn: Boolean?,
    val territories: List<Territory>
)
