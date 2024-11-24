package bme.aut.sza.honfoglalo.ui.questions.answerpicking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Popup
import bme.aut.sza.honfoglalo.data.entities.Category
import bme.aut.sza.honfoglalo.data.entities.QuestionType
import bme.aut.sza.honfoglalo.ui.model.AnswerUi
import bme.aut.sza.honfoglalo.ui.model.QuestionUi
import bme.aut.sza.honfoglalo.ui.questions.QuestionBox
import bme.aut.sza.honfoglalo.ui.theme.Shade

@Composable
fun AnswerPickingQuestion(
    question: QuestionUi,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onAnswerSelected: (String) -> Unit
) {
    Popup(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Shade),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            QuestionBox(
                question = question.question,
                category = question.category,
            )

            Spacer(modifier = modifier.fillMaxHeight(0.1f))

            AnswerPicker(
                answers = question.answers,
                onClick = onAnswerSelected
            )
        }
    }
}

@Preview
@Composable
fun AnswerPickingQuestionPreview() {
    val q =
        QuestionUi(
            question = "rnaőjgn őromfpweomfvjih lfvepvuvpfiei hngfyrfewísfwedfí feígbyvwrvyeberg yergihgb úp?",
            answers = listOf(AnswerUi("Answer 1"), AnswerUi("Answer 2"), AnswerUi("Answer 3"), AnswerUi("Answer 4")),
            category = Category.ENTERTAINMENT,
            type = QuestionType.ANSWER_PICK
        )

    Box(modifier = Modifier) {
        AnswerPickingQuestion(question = q, onAnswerSelected = {})
    }
}
