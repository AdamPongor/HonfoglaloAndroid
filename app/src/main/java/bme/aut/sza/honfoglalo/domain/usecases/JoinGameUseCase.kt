package bme.aut.sza.honfoglalo.domain.usecases

import bme.aut.sza.honfoglalo.data.repositories.GamesRepository
import bme.aut.sza.honfoglalo.domain.model.JoinData
import bme.aut.sza.honfoglalo.domain.model.asJoinGameEntity
import java.io.IOException

class JoinGameUseCase(private val repository: GamesRepository) {
    suspend operator fun invoke(joinData: JoinData): Result<Nothing?> {
        return try {
            repository.joinGame(joinData.asJoinGameEntity())
            Result.success(null)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}