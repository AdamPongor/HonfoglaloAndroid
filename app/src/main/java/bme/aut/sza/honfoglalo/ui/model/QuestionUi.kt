package bme.aut.sza.honfoglalo.ui.model

import bme.aut.sza.honfoglalo.data.entities.Category
import bme.aut.sza.honfoglalo.data.entities.QuestionType
import bme.aut.sza.honfoglalo.domain.model.Question


data class QuestionUi(
    val question: String = "",
    val answers: List<AnswerUi>,
    val category: Category,
    val type: QuestionType
)

fun Question.asQuestionUi() : QuestionUi = QuestionUi(
    question = question,
    answers = answers.map { it.asAnswerUi() },
    category = category,
    type = type
)
