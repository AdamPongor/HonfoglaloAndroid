package bme.aut.sza.honfoglalo.ui.questions.answerpicking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import bme.aut.sza.honfoglalo.ui.common.AnswerButton
import bme.aut.sza.honfoglalo.ui.model.AnswerUi

@Composable
fun AnswerPicker(
    answers: List<AnswerUi>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        for (i in answers.indices step 2) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                AnswerButton(answer = answers[i].answer, modifier = Modifier.weight(1f), onClick = { onClick(answers[i].answer) })
                if (i < answers.size - 1) {
                    AnswerButton(answer = answers[i + 1].answer, modifier = Modifier.weight(1f), onClick = { onClick(answers[i + 1].answer) })
                }
            }
        }
    }
}
