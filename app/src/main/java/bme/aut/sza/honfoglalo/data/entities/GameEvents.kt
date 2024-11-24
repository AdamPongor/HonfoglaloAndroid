package bme.aut.sza.honfoglalo.data.entities

enum class GameEvents (val Name: String){
    JOIN_GAME("joinGame"),
    LEAVE_GAME("leaveGame"),
    ANSWER_QUESTION("answerQuestion"),
    SELECT_TERRITORY("selectTerritory"),
    REQUEST_UPDATE("requestUpdate"),
    GAME_UPDATED("gameUpdated"),
    ERROR("error")
}