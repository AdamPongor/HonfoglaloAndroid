package bme.aut.sza.honfoglalo.data.entities

enum class GameEvents (val Name: String){
    CREATE_GAME("createGame"),
    JOIN_GAME("joinGame"),
    LEAVE_GAME("leaveGame"),
    START_QUESTION_SELECTION("startQuestionSelection"),
    START_ANSWERING_QUESTION("startAnsweringQuestion"),
    ANSWER_QUESTION("answerQuestion"),
    START_TERRITORY_SELECTION("startTerritorySelection"),
    SELECT_TERRITORY("selectTerritory"),
    REQUEST_UPDATE("requestUpdate"),
    GAME_UPDATED("gameUpdated")
}