package bme.aut.sza.honfoglalo.data.di

import bme.aut.sza.honfoglalo.data.datasource.UserPreferencesDataSource
import bme.aut.sza.honfoglalo.data.datasource.WebSocketRemoteDataSource
import bme.aut.sza.honfoglalo.data.repositories.GamesRepository
import bme.aut.sza.honfoglalo.data.repositories.GamesRepositoryImpl
import bme.aut.sza.honfoglalo.data.repositories.UserPreferences
import bme.aut.sza.honfoglalo.data.util.SocketHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideGamesRepository(
        webSocketRemoteDataSource: WebSocketRemoteDataSource,
        userPreferencesDataSource: UserPreferencesDataSource
    ): GamesRepository = GamesRepositoryImpl(webSocketRemoteDataSource, userPreferencesDataSource)

    @Singleton
    @Provides
    fun provideWebSocketRemoteDataSource(
        socketHandler: SocketHandler,
        userPreferencesDataSource: UserPreferencesDataSource
    ): WebSocketRemoteDataSource = WebSocketRemoteDataSource(socketHandler, userPreferencesDataSource)

    @Singleton
    @Provides
    fun provideUserPreferencesDataSource(
        userPreferences: UserPreferences
    ): UserPreferencesDataSource = UserPreferencesDataSource(userPreferences)
}