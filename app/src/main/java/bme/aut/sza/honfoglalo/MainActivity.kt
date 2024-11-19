package bme.aut.sza.honfoglalo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import bme.aut.sza.honfoglalo.data.util.SocketHandler
import bme.aut.sza.honfoglalo.navigation.NavGraph
import bme.aut.sza.honfoglalo.ui.theme.HonfoglaloTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var socketHandler: SocketHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*lifecycleScope.launch {
            socketHandler.setSocket()
            socketHandler.establishConnection()
        }*/

        setContent {
            HonfoglaloTheme {
                NavGraph()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background,
//                ) {
//                    val mSocket = SocketHandler.getSocket()
//                    Button(onClick = {
//                        val data = JSONObject().apply {
//                            put("code", "dbim7o")
//                            put("name", "Varesz")
//                        }
//                        mSocket.emit("joinGame", data)
//                    }) {
//                        Text("COME ON WORK!!!")
//                    }
//                   //  GameScreen(players, 10, 4)
//                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socketHandler.closeConnection()
    }
}