package bme.aut.sza.honfoglalo.domain.model

import bme.aut.sza.honfoglalo.data.entities.QuestionEntity


data class Question(
    val question: String,
    // val category: Category?,
    val answers: List<Answer>,
)

fun QuestionEntity.asQuestion(): Question = Question(
    question = question,
    answers = answers.map { it.asAnswer() }
)
