package bme.aut.sza.honfoglalo.domain.usecases

import bme.aut.sza.honfoglalo.data.repositories.GamesRepository
import bme.aut.sza.honfoglalo.domain.model.Answer
import bme.aut.sza.honfoglalo.domain.model.asAnswerEntity

class AnswerQuestionUseCase(private val gamesRepository: GamesRepository) {
    suspend operator fun invoke(answer: Answer): Result<Nothing?> {
        return try {
            gamesRepository.answerQuestion(answer.asAnswerEntity())
            Result.success(null)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}