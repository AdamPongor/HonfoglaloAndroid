package bme.aut.sza.honfoglalo.domain.usecases

import bme.aut.sza.honfoglalo.data.repositories.GamesRepository
import bme.aut.sza.honfoglalo.domain.model.GameData
import bme.aut.sza.honfoglalo.domain.model.asGameData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HandleGameUseCase(private val repository: GamesRepository) {
    suspend operator fun invoke(): Flow<Result<GameData>> {
        return repository.gameHandling().map { gameState ->
            try {
                val state = gameState.asGameData()
                Result.success(state)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}