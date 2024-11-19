package bme.aut.sza.honfoglalo.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import bme.aut.sza.honfoglalo.data.repositories.UserPreferences
import bme.aut.sza.honfoglalo.data.repositories.UserPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserPreferencesModule {

    @Provides
    @Singleton
    fun provideUserDataStorePreferences(
        @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> = applicationContext.userDataStore

    @Provides
    @Singleton
    fun provideUserPreferencesImpl(
        dataStore: DataStore<Preferences>
    ): UserPreferencesImpl = UserPreferencesImpl(dataStore)

    @Provides
    @Singleton
    fun provideUserPreferences(
        userPreferencesImpl: UserPreferencesImpl
    ): UserPreferences = userPreferencesImpl
}

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    // just my preference of naming including the package name
    name = "user_preferences"
)