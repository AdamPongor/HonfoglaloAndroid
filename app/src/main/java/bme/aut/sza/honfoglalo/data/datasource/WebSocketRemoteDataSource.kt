package bme.aut.sza.honfoglalo.data.datasource

import android.util.Log
import bme.aut.sza.honfoglalo.data.entities.GameDataEntity
import bme.aut.sza.honfoglalo.data.entities.GameEvents
import bme.aut.sza.honfoglalo.data.entities.GameStates
import bme.aut.sza.honfoglalo.data.entities.JoinGameEntity
import bme.aut.sza.honfoglalo.data.entities.PlayerEntity
import bme.aut.sza.honfoglalo.data.entities.Question
import bme.aut.sza.honfoglalo.data.util.SocketHandler
import bme.aut.sza.honfoglalo.data.util.WebSocketDataParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
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
                            val myPlayer = WebSocketDataParser.myPlayer(args)
                            CoroutineScope(Dispatchers.IO).launch {
                                userPreferencesDataSource.saveUserId(myPlayer)
                            }
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

        socket.emit(GameEvents.REQUEST_UPDATE.Name, JSONObject())

        socket.on(GameEvents.GAME_UPDATED.Name) { args ->
            try {
                val state = WebSocketDataParser.parseGameState(args)
                if (state != null) {
                    when (state) {
                        GameStates.LOBBY -> {
                            val players = WebSocketDataParser.parsePlayers(args)
                            trySend(Pair(state, players))
                        }
                        GameStates.CHOOSING_QUESTION -> {
                            val players = WebSocketDataParser.parsePlayers(args)
                            trySend(Pair(state, players))
                            socket.off(GameEvents.GAME_UPDATED.Name)
                        }
                        else -> { }
                    }

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

    fun gameFlowHandling(): Flow<GameDataEntity> = callbackFlow {
        val socket = socketHandler.getSocket()

        socket.emit(GameEvents.REQUEST_UPDATE.Name, JSONObject())

        socket.on(GameEvents.GAME_UPDATED.Name) { args ->
            try {
                val state = WebSocketDataParser.parseGameState(args)
                if (state != null) {
                    val players = WebSocketDataParser.parsePlayers(args)
                    when(state) {
                        GameStates.CHOOSING_QUESTION -> {
                            val gameData = GameDataEntity(state, players, null, null)
                            trySend(gameData)
                        }
                        GameStates.ANSWERING_QUESTION -> {
                            val question = WebSocketDataParser.parseQuestion(args)
                            val gameData = GameDataEntity(state, players, question, null)
                            trySend(gameData)
                        }
                        GameStates.TERRITORY_SELECTION -> {
                            val territorySelection = WebSocketDataParser.parseTerritorySelection(args)
                            CoroutineScope(Dispatchers.IO).launch {
                                val myUser = userPreferencesDataSource.getUserId()
                                val isMyTurn = territorySelection.territorySelection.first().id == myUser
                                val gameData = GameDataEntity(state, players, null, isMyTurn)
                                trySend(gameData)
                            }
                        }
                        else-> { }
                    }
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

    fun answerQuestion(answer: String) {
        val socket = socketHandler.getSocket()
        socket.emit(GameEvents.ANSWER_QUESTION.Name, JSONObject().apply {
            put("answer", answer)
        })
    }

    fun selectTerritory(territoryName: String) {
        val socket = socketHandler.getSocket()
        socket.emit(GameEvents.SELECT_TERRITORY.Name, JSONObject().apply {
            put("territory", territoryName)
        })
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