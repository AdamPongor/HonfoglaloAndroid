package bme.aut.sza.honfoglalo.data.datasource


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// domain layer
interface UserPreferences {

    suspend fun <T> setPreference(
        name: T,
        key: Preferences.Key<T>
    )

    suspend fun <T> getPreference(key: Preferences.Key<T>): Flow<T?>


}

// data layer
class PreferencesImpl @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>
) : UserPreferences {

    val USERNAME = stringPreferencesKey(name = "USERNAME")
    val serverIP = stringPreferencesKey(name = "SERVERIP")

    override suspend fun <T> setPreference(
        name: T,
        key: Preferences.Key<T>
    ) {
        Result.runCatching {
            userDataStorePreferences.edit { preferences ->
                preferences[key] = name
            }
        }
    }

    override suspend fun <T> getPreference(key: Preferences.Key<T>): Flow<T?> {
        return userDataStorePreferences.data
            .map { preferences ->
                // Get our name value, defaulting to "" if not set
                preferences[key]
            }
    }


}