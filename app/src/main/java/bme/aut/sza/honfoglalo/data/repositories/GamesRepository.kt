package bme.aut.sza.honfoglalo.data.repositories

import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.JoinGameEntity

interface GamesRepository {
    suspend fun joinGame(joinGameEntity: JoinGameEntity): GameStates
    suspend fun leaveGame()
}