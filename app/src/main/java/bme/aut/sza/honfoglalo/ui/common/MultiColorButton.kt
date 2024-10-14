package bme.aut.sza.honfoglalo.ui.common

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bme.aut.sza.honfoglalo.ui.theme.HonfoglaloTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MultiColorButton(
    modifier: Modifier = Modifier,
    colors: Array<Color>,
    text: String,
    onClick: () -> Unit,
) {
    val stops = mutableListOf<Pair<Float, Color>>()

    // stops.add(0.0f to colors[0])

    for (index in colors.indices) {
        stops.add((index / colors.size.toFloat() to colors[index]))
        stops.add(((index + 1) / colors.size.toFloat() to colors[index]))
    }
    Log.d("xd", stops.toString())
    // stops.add(1.0f to colors[colors.lastIndex])

    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
        border = BorderStroke(color = Color.Black, width = 3.dp),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(colorStops = stops.toTypedArray()),
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            OutlinedText(
                text = text,
                fontSize = 20.sp,
                fillColor = Color.Black,
                outlineColor = Color.White,
                outlineDrawStyle = Stroke(width = 5f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    HonfoglaloTheme {
        MultiColorButton(
            colors = arrayOf(Color.Green, Color.Blue),
            text = "Ez egy hosszú válasz xdddd",
            onClick = { },
        )
    }
}
