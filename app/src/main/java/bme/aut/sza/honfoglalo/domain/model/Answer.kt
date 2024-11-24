package bme.aut.sza.honfoglalo.domain.model

import bme.aut.sza.honfoglalo.data.entities.AnswerEntity

data class Answer(
    val answer: String
)

fun Answer.asAnswerEntity(): AnswerEntity = AnswerEntity(
    answer = answer
)

fun AnswerEntity.asAnswer(): Answer = Answer(
    answer = answer
)


