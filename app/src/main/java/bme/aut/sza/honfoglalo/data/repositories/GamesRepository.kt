package bme.aut.sza.honfoglalo.data.repositories

import bme.aut.sza.honfoglalo.data.entities.GameDataEntity
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.JoinGameEntity
import bme.aut.sza.honfoglalo.data.entities.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun loadUsername(): String
    suspend fun joinGame(joinGameEntity: JoinGameEntity)
    suspend fun lobbyWaiting(): Flow<Pair<GameStates, List<PlayerEntity>>>
    suspend fun leaveGame()
    suspend fun gameHandling(): Flow<GameDataEntity>
    suspend fun answerQuestion(answer: String)
}