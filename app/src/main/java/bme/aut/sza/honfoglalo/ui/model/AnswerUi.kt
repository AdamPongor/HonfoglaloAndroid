package bme.aut.sza.honfoglalo.ui.model

import bme.aut.sza.honfoglalo.domain.model.Answer

data class AnswerUi(
    val answer: String = ""
)

fun AnswerUi.asAnswer(): Answer = Answer (
    answer = answer
)

fun Answer.asAnswerUi(): AnswerUi = AnswerUi(
    answer = answer
)
