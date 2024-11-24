package bme.aut.sza.honfoglalo.domain.model

import bme.aut.sza.honfoglalo.data.entities.Category
import bme.aut.sza.honfoglalo.data.entities.QuestionEntity
import bme.aut.sza.honfoglalo.data.entities.QuestionType


data class Question(
    val question: String,
    val answers: List<Answer>,
    val category: Category,
    val type: QuestionType
)

fun QuestionEntity.asQuestion(): Question = Question(
    question = question,
    answers = possibleAnswers.map { it.asAnswer() },
    category = category,
    type = type
)
