package bme.aut.sza.honfoglalo.data.util

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import bme.aut.sza.honfoglalo.data.datasource.PreferencesImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import java.net.URISyntaxException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketHandler @Inject constructor(
    private val prefs: PreferencesImpl
) {
    private lateinit var mSocket: Socket

    fun setSocket(ip: String) {
        try {
            mSocket = IO.socket("http://${ip}:3000")
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