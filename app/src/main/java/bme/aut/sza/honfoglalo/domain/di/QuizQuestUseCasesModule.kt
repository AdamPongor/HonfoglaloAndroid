package bme.aut.sza.honfoglalo.domain.di

import bme.aut.sza.honfoglalo.data.repositories.GamesRepository
import bme.aut.sza.honfoglalo.domain.usecases.JoinGameUseCase
import bme.aut.sza.honfoglalo.domain.usecases.LeaveGameUseCase
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
    fun provideLeaveGameUseCase(): LeaveGameUseCase = LeaveGameUseCase()

    @Provides
    @Singleton
    fun provideQuizQuestUseCases(
        joinGameUseCase: JoinGameUseCase,
        leaveGameUseCase: LeaveGameUseCase
    ): QuizQuestUseCases = QuizQuestUseCases(joinGameUseCase, leaveGameUseCase)
}