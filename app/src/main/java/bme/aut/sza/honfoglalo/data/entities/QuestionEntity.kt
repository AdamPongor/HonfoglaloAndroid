package bme.aut.sza.honfoglalo.data.entities

data class QuestionEntity(
    val question: String,
    // val category: Category?,
    val answers: List<AnswerEntity>,
)
