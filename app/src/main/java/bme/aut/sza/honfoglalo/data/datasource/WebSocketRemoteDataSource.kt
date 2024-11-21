package bme.aut.sza.honfoglalo.data.datasource

import android.util.Log
import bme.aut.sza.honfoglalo.data.entities.GameEvents
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.JoinGameEntity
import bme.aut.sza.honfoglalo.data.entities.PlayerEntity
import bme.aut.sza.honfoglalo.data.util.SocketHandler
import bme.aut.sza.honfoglalo.data.util.WebSocketDataParser
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class WebSocketRemoteDataSource(
    private val socketHandler: SocketHandler,
    private val userPreferencesDataSource: UserPreferencesDataSource
) {
    suspend fun joinGame(joinData: JoinGameEntity) {
        try {
            val ipAddress = userPreferencesDataSource.getIPAddress()
            socketHandler.setSocket(ipAddress)
            socketHandler.establishConnection()
            val socket = socketHandler.getSocket()
            socket.emit(GameEvents.JOIN_GAME.Name,  JSONObject().apply {
                    put("code", joinData.gameCode)
                    put("name", joinData.username)
                }
            )

            userPreferencesDataSource.saveUsername(joinData.username)

            return suspendCoroutine { continuation ->
                socket.once(GameEvents.GAME_UPDATED.Name) { args ->
                    try {
                        val state = WebSocketDataParser.parseGameState(args)
                        if (state != null && state == GameStates.LOBBY) {
                            continuation.resume(Unit)
                        } else {
                            continuation.resumeWithException(
                                IllegalArgumentException("Invalid game state received: $state")
                            )
                        }
                    } catch (e: Exception) {
                        continuation.resumeWithException(e)
                    }
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun lobbyWaiting(): Flow<Pair<GameStates, List<PlayerEntity>>> = callbackFlow {
        val socket = socketHandler.getSocket()

        socket.on(GameEvents.GAME_UPDATED.Name) { args ->
            try {
                val state = WebSocketDataParser.parseGameState(args)
                if (state != null && (state == GameStates.LOBBY || state == GameStates.CHOOSING_QUESTION)) {
                    val players = WebSocketDataParser.parsePlayers(args)
                    trySend(Pair(state, players))
                } else {
                    close(IllegalArgumentException("Invalid game state received: $state"))
                }
            } catch (e: Exception) {
                close(e)
            }
        }

        awaitClose {
            socket.off(GameEvents.GAME_UPDATED.Name)
        }
    }

    fun leaveGame() {
        try {
            val socket = socketHandler.getSocket()
            socket.emit(GameEvents.LEAVE_GAME.Name)
            socketHandler.closeConnection()
        } catch (e: Exception) {
            throw e
        }
    }
}