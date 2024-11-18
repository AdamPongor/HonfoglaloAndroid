package bme.aut.sza.honfoglalo.data.di

import android.content.Context
import bme.aut.sza.honfoglalo.data.datasource.PreferencesImpl
import bme.aut.sza.honfoglalo.data.util.SocketHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {

    @Provides
    @Singleton
    fun provideWebSocketHandler(
        prefs: PreferencesImpl
    ): SocketHandler {
        return SocketHandler(prefs)
    }
}