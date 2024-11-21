package bme.aut.sza.honfoglalo.domain.usecases

import bme.aut.sza.honfoglalo.data.repositories.GamesRepository

class AnswerQuestionUseCase(private val gamesRepository: GamesRepository) {
    suspend operator fun invoke(answer: String): Result<Nothing?> {
        return try {
            gamesRepository.answerQuestion(answer)
            Result.success(null)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}