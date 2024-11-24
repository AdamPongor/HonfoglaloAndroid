package bme.aut.sza.honfoglalo.domain.usecases

import bme.aut.sza.honfoglalo.data.repositories.GamesRepository
import java.io.IOException

class LoadUsernameUseCase(private val repository: GamesRepository) {
    suspend operator fun invoke(): Result<String> {
        return try {
            val username = repository.loadUsername()
            Result.success(username)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}