package bme.aut.sza.honfoglalo.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoundCounter(
    totalRounds: Int,
    currentRound: Int,
) {
    Box(
        modifier =
            Modifier
                .size(100.dp)
                .padding(4.dp)
                .border(width = 2.dp, color = Color.Black, shape = CutCornerShape(8.dp))
                .clip(CutCornerShape(8.dp))
                .background(color = Color(0xFFD2B48C)),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = "Rounds",
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp),
            )

            Box(contentAlignment = Alignment.Center) {
                Canvas(modifier = Modifier.size(60.dp).padding(0.dp)) {
                    drawCircle(
                        color = Color.Gray,
                        style = Stroke(width = 10f),
                    )

                    val sweepAngle = 360 * (currentRound.toFloat() / totalRounds)

                    drawArc(
                        color = Color.Green,
                        startAngle = -90f,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        style = Stroke(width = 10f),
                    )
                }
                Text(
                    text = "$currentRound/$totalRounds",
                    fontSize = 16.sp,
                    color = Color.Black,
                )
            }
        }
    }
}

@Preview
@Composable
fun RoundCounterPreview() {
    RoundCounter(totalRounds = 10, currentRound = 3)
}
