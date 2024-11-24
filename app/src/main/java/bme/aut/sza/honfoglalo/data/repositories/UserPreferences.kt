package bme.aut.sza.honfoglalo.data.repositories

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val USERNAME: Preferences.Key<String>
    val userId: Preferences.Key<String>
    val serverIP: Preferences.Key<String>

    suspend fun <T> setPreference(
        name: T,
        key: Preferences.Key<T>
    )

    suspend fun <T> getPreference(key: Preferences.Key<T>): Flow<T?>
}