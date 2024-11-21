package bme.aut.sza.honfoglalo.data.entities

data class Question(
    val question: String,
    // val category: Category?,
    val answers: List<String>,
)
