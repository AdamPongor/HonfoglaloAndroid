package bme.aut.sza.honfoglalo.domain.usecases

import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.repositories.GamesRepository
import bme.aut.sza.honfoglalo.domain.model.Player
import bme.aut.sza.honfoglalo.domain.model.asPlayer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LobbyWaitingUseCase(private val repository: GamesRepository) {
    suspend operator fun invoke(): Flow<Result<Pair<GameStates, List<Player>>>> {
        return repository.lobbyWaiting().map { (state, players) ->
            try {
                val mappedPlayers = players.map { it.asPlayer() }
                Result.success(Pair(state, mappedPlayers))
            } catch (e: Throwable) {
                Result.failure(e)
            }
        }
    }
}