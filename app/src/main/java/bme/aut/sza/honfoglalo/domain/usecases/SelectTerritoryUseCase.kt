package bme.aut.sza.honfoglalo.domain.usecases

import bme.aut.sza.honfoglalo.data.repositories.GamesRepository

class SelectTerritoryUseCase(private val repository: GamesRepository) {
    suspend operator fun invoke(territory: String): Result<Nothing?> {
        return try {
            repository.selectTerritory(territory)
            Result.success(null)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}