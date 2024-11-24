package bme.aut.sza.honfoglalo.domain.usecases

import bme.aut.sza.honfoglalo.data.repositories.GamesRepository
import java.io.IOException

class LeaveGameUseCase(
    private val repository: GamesRepository
) {
    suspend operator fun invoke(): Result<Nothing?> {
        return try {
            repository.leaveGame()
            Result.success(null)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}