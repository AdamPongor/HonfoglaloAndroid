package bme.aut.sza.honfoglalo.data.repositories

import bme.aut.sza.honfoglalo.data.datasource.WebSocketRemoteDataSource
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.JoinGameEntity

class GamesRepositoryImpl(
    private val dataSource: WebSocketRemoteDataSource
): GamesRepository {
    override suspend fun joinGame(joinGameEntity: JoinGameEntity): GameStates {
        return dataSource.joinGame(joinData = joinGameEntity)
    }

    override suspend fun leaveGame() {
        // TODO("Not yet implemented")
    }
}