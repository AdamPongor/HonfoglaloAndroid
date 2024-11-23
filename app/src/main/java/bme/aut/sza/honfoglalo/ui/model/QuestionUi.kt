package bme.aut.sza.honfoglalo.ui.model

import bme.aut.sza.honfoglalo.domain.model.Question


data class QuestionUi(
    val question: String = "",
    // val category: Category?,
    val answers: List<AnswerUi>,
)

fun Question.asQuestionUi() : QuestionUi = QuestionUi(
    question = question,
    answers = answers.map { it.asAnswerUi() }
)
