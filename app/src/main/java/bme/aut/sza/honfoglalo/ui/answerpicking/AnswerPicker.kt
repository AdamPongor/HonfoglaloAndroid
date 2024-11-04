package bme.aut.sza.honfoglalo.ui.answerpicking

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import bme.aut.sza.honfoglalo.ui.common.AnswerButton

@Composable
fun AnswerPicker(
    answers: List<String>,
    modifier: Modifier = Modifier,
) {

    /*LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier =
        modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        items(answers) { answer ->
            AnswerButton(answer = answer)
        }
    }*/

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        for (i in answers.indices step 2) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                AnswerButton(answer = answers[i], modifier = Modifier.weight(1f))
                if (i < answers.size - 1) {
                    AnswerButton(answer = answers[i + 1], modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
fun AnswerPickerPreview() {
    val answers = listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4", "xdd")
    AnswerPicker(answers = answers)
}
