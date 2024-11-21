package bme.aut.sza.honfoglalo.domain.di

import bme.aut.sza.honfoglalo.data.repositories.GamesRepository
import bme.aut.sza.honfoglalo.domain.usecases.HandleGameUseCase
import bme.aut.sza.honfoglalo.domain.usecases.JoinGameUseCase
import bme.aut.sza.honfoglalo.domain.usecases.LeaveGameUseCase
import bme.aut.sza.honfoglalo.domain.usecases.LoadUsernameUseCase
import bme.aut.sza.honfoglalo.domain.usecases.LobbyWaitingUseCase
import bme.aut.sza.honfoglalo.domain.usecases.QuizQuestUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QuizQuestUseCasesModule {
    @Provides
    @Singleton
    fun provideJoinGameUseCase(
        gamesRepository: GamesRepository
    ): JoinGameUseCase = JoinGameUseCase(gamesRepository)

    @Provides
    @Singleton
    fun provideLeaveGameUseCase(
        gamesRepository: GamesRepository
    ): LeaveGameUseCase = LeaveGameUseCase(gamesRepository)

    @Provides
    @Singleton
    fun provideLoadUsernameUseCase(
        gamesRepository: GamesRepository
    ): LoadUsernameUseCase = LoadUsernameUseCase(gamesRepository)

    @Singleton
    @Provides
    fun provideLobbyWaitingUseCase(
        gamesRepository: GamesRepository
    ): LobbyWaitingUseCase = LobbyWaitingUseCase(gamesRepository)

    @Singleton
    @Provides
    fun provideHandleGameUseCase(
        gamesRepository: GamesRepository
    ): HandleGameUseCase = HandleGameUseCase(gamesRepository)

    @Provides
    @Singleton
    fun provideQuizQuestUseCases(
        loadUsernameUseCase: LoadUsernameUseCase,
        joinGameUseCase: JoinGameUseCase,
        lobbyWaitingUseCase: LobbyWaitingUseCase,
        leaveGameUseCase: LeaveGameUseCase,
        handleGameUseCase: HandleGameUseCase,
    ): QuizQuestUseCases
    = QuizQuestUseCases(
        loadUsernameUseCase,
        joinGameUseCase,
        lobbyWaitingUseCase,
        leaveGameUseCase,
        handleGameUseCase
    )
}