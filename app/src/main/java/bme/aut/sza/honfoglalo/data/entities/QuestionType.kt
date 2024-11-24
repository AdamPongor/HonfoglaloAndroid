package bme.aut.sza.honfoglalo.data.entities

enum class QuestionType(val type: String) {
    GUESS("Guess"),
    ANSWER_PICK("AnswerPick");

    companion object {
        fun fromString(category: String): QuestionType {
            return QuestionType.entries.find { it.type.equals(category, ignoreCase = true) }!!
        }
    }
}