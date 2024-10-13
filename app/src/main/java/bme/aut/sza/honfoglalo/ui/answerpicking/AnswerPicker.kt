package bme.aut.sza.honfoglalo.ui.answerpicking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bme.aut.sza.honfoglalo.ui.common.AnswerButton

@Composable
fun AnswerPicker(
    answers: List<String>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(answers) { answer ->
            AnswerButton(answer = answer)
        }
    }
}

@Preview
@Composable
fun AnswerPickerPreview() {
    val answers = listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4")
    AnswerPicker(answers = answers)
}