package bme.aut.sza.honfoglalo.data.datasource

import bme.aut.sza.honfoglalo.data.repositories.UserPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn

class UserPreferencesDataSource(
    private val userPreferences: UserPreferences
) {
    suspend fun saveUsername(username: String) {
        userPreferences.setPreference(name = username, key = userPreferences.USERNAME)
    }

    suspend fun getUsername(): String {
        return userPreferences.getPreference(key = userPreferences.USERNAME)
            .stateIn(CoroutineScope(Dispatchers.IO)).value ?: ""
    }

    suspend fun saveUserId(userId: String) {
        userPreferences.setPreference(name = userId, key = userPreferences.userId)
    }

    suspend fun getUserId(): String {
        return userPreferences.getPreference(key = userPreferences.userId)
            .stateIn(CoroutineScope(Dispatchers.IO)).value ?: ""
    }

    suspend fun saveIPAddress(ipAddress: String) {
        userPreferences.setPreference(name = ipAddress, key = userPreferences.serverIP)
    }

    suspend fun getIPAddress(): String {
        return userPreferences.getPreference(key = userPreferences.serverIP)
            .stateIn(CoroutineScope(Dispatchers.IO)).value ?: "10.0.2.2"
    }
}