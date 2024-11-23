package bme.aut.sza.honfoglalo.data.entities

data class GameDataEntity(
    val state: GameStates,
    val playerEntity: List<PlayerEntity>,
    val question: QuestionEntity?,
    val myTurn: Boolean?
)
