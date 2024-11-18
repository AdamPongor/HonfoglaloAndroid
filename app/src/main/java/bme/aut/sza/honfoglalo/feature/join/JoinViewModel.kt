package bme.aut.sza.honfoglalo.feature.join

import androidx.lifecycle.ViewModel
import bme.aut.sza.honfoglalo.data.datasource.PreferencesImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val prefs: PreferencesImpl
) : ViewModel() {


    suspend fun saveUsername(name: String) {
        prefs.setPreference(name = name, key = prefs.USERNAME)
    }

    suspend fun getUsername(): String {
        return prefs.getPreference(key = prefs.USERNAME)
            .stateIn(CoroutineScope(Dispatchers.IO)).value ?: ""
    }

    fun ConnectWebSocket() {
        /*TODO*/
    }
}