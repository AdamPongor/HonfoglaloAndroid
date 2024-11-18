package bme.aut.sza.honfoglalo.data.di

import bme.aut.sza.honfoglalo.data.util.SocketHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object WebSocketModule {
    @Provides
    fun provideWebSocketHandler(): SocketHandler = SocketHandler
}