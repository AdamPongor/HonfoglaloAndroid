package bme.aut.sza.honfoglalo.ui.map

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import bme.aut.sza.honfoglalo.data.entities.County
import bme.aut.sza.honfoglalo.ui.util.createCountyPath
import bme.aut.sza.honfoglalo.ui.util.isPointInsideCounty

@Composable
fun GameMap(
    counties: List<County>,
    onCountyClick: (County) -> Unit,
    scale: Float,
) {
    var clickedRegion: County? by remember { mutableStateOf(null) }
    val clickPosition = remember { mutableStateOf(Offset.Zero) }

    Canvas(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    clickPosition.value = offset
                }
            },
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Iterate through counties and draw them on the canvas
        counties.forEach { county ->
            val path = createCountyPath(county, canvasWidth, canvasHeight, scale)

            // Fill the county with color
            drawPath(
                path = path,
                color = county.color,
            )

            // Draw county borders
            drawPath(
                path = path,
                color = Color.Black,
                style = Stroke(width = 2.dp.toPx()),
            )

            if (isPointInsideCounty(path, clickPosition.value)) {
                clickedRegion = county
            }
        }
    }

    LaunchedEffect(clickedRegion) {
        clickedRegion?.let { onCountyClick(it) }
    }
}
