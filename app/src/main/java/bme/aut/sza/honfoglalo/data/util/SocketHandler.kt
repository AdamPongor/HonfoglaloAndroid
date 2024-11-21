package bme.aut.sza.honfoglalo.data.util

import bme.aut.sza.honfoglalo.data.repositories.UserPreferencesImpl
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketHandler(
    private val preferences: UserPreferencesImpl
) {
    private lateinit var mSocket: Socket

    fun setSocket(ip: String) {
        try {
            mSocket = IO.socket("https://quizquest-backend.up.railway.app/")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}