package bme.aut.sza.honfoglalo.data.di

import bme.aut.sza.honfoglalo.data.repositories.UserPreferencesImpl
import bme.aut.sza.honfoglalo.data.util.SocketHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {

    @Provides
    @Singleton
    fun provideWebSocketHandler(
        prefs: UserPreferencesImpl
    ): SocketHandler = SocketHandler(prefs)
}