package bme.aut.sza.honfoglalo.domain.usecases

import android.util.Log
import bme.aut.sza.honfoglalo.data.repositories.GamesRepository
import bme.aut.sza.honfoglalo.domain.model.Territory
import bme.aut.sza.honfoglalo.domain.model.asTerritoryEntity

class SelectTerritoryUseCase(private val repository: GamesRepository) {
    suspend operator fun invoke(territory: Territory): Result<Nothing?> {
        return try {
            Log.d("Territory: ", territory.territory)
            repository.selectTerritory(territory.asTerritoryEntity())
            Result.success(null)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}