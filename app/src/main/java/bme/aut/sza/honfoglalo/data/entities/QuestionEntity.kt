package bme.aut.sza.honfoglalo.data.entities

data class QuestionEntity(
    val question: String,
    val possibleAnswers: List<AnswerEntity>,
    val category: Category,
    val type: QuestionType
)
