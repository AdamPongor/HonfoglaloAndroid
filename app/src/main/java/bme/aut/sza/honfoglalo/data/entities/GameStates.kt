package bme.aut.sza.honfoglalo.data.entities

enum class GameStates(val Name: String) {
    LOBBY("LOBBY"),
    INCORRECT_ROOM("INCORRECT_ROOM"),
    CHOOSING_QUESTION("CHOOSING_QUESTION"),
    ANSWERING_QUESTION("ANSWERING_QUESTION"),
    TERRITORY_SELECTION("TERRITORY_SELECTION"),
    END("END"),
}