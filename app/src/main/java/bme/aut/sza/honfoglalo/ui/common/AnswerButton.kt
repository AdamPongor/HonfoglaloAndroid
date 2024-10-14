package bme.aut.sza.honfoglalo.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bme.aut.sza.honfoglalo.ui.theme.Tan

@Composable
fun AnswerButton(
    answer: String,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = { onClick() },
        shape = CutCornerShape(10.dp),
        modifier =
            Modifier
                .fillMaxWidth()
                .aspectRatio(2f)
                .padding(4.dp)
                .border(width = 3.dp, color = Color.Black, shape = CutCornerShape(10.dp)),
        colors = ButtonDefaults.buttonColors(Color.Gray),
    ) {
        AutoResizeText(
            text = answer,
            color = Color.Black,
            modifier =
                Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            fontSizeRange = FontSizeRange(15.sp, 30.sp),
        )
    }
}

@Preview
@Composable
fun AnswerPreview() {
    AnswerButton("Answer")
}
