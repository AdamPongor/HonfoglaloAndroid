package bme.aut.sza.honfoglalo.data.repositories

import bme.aut.sza.honfoglalo.data.datasource.UserPreferencesDataSource
import bme.aut.sza.honfoglalo.data.datasource.WebSocketRemoteDataSource
import bme.aut.sza.honfoglalo.data.entities.AnswerEntity
import bme.aut.sza.honfoglalo.data.entities.GameDataEntity
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.JoinGameEntity
import bme.aut.sza.honfoglalo.data.entities.PlayerEntity
import bme.aut.sza.honfoglalo.data.entities.TerritoryEntity
import kotlinx.coroutines.flow.Flow

class GamesRepositoryImpl(
    private val webSocketRemoteDataSource: WebSocketRemoteDataSource,
    private val userPreferencesDataSource: UserPreferencesDataSource
): GamesRepository {
    override suspend fun loadUsername(): String {
        return userPreferencesDataSource.getUsername()
    }
    override suspend fun joinGame(joinGameEntity: JoinGameEntity) {
        return webSocketRemoteDataSource.joinGame(joinData = joinGameEntity)
    }

    override suspend fun lobbyWaiting(): Flow<Pair<GameStates, List<PlayerEntity>>> {
        return webSocketRemoteDataSource.lobbyWaiting()
    }

    override suspend fun leaveGame() {
        return webSocketRemoteDataSource.leaveGame()
    }

    override suspend fun gameHandling(): Flow<GameDataEntity> {
        return webSocketRemoteDataSource.gameFlowHandling()
    }

    override suspend fun answerQuestion(answer: AnswerEntity) {
        return webSocketRemoteDataSource.answerQuestion(answer)
    }

    override suspend fun selectTerritory(territory: TerritoryEntity) {
        return webSocketRemoteDataSource.selectTerritory(territory)
    }
}