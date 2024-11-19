package bme.aut.sza.honfoglalo.data.datasource

import bme.aut.sza.honfoglalo.data.entities.GameEvents
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.JoinGameEntity
import bme.aut.sza.honfoglalo.data.util.SocketHandler
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class WebSocketRemoteDataSource(
    private val socketHandler: SocketHandler,
    private val userPreferencesDataSource: UserPreferencesDataSource
) {
    suspend fun joinGame(joinData: JoinGameEntity): GameStates {
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
                        val response = JSONObject(args[0].toString())
                        val game = response.getJSONObject("game")
                        val gameState = game.getString("state")

                        val state = GameStates.entries.find { it.Name == gameState }
                        if (state != null) {
                            continuation.resume(state)
                        } else {
                            continuation.resumeWithException(
                                IllegalArgumentException("Invalid game state received: $gameState")
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
}