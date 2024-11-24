package bme.aut.sza.honfoglalo.ui.util

import android.content.Context
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Region
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import bme.aut.sza.honfoglalo.data.entities.County
import bme.aut.sza.honfoglalo.data.entities.GeoJson
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun loadGeoJson(context: Context): List<County> =
    withContext(Dispatchers.IO) {
        context.assets.open(Constants.GEOJSON_FILE_NAME).use { inputStream ->
            val geoJson = Gson().fromJson(inputStream.reader(), GeoJson::class.java)
            geoJson.features.map { feature ->
                County(
                    name = feature.properties?.get("name").toString(),
                    coordinates = feature.geometry.coordinates,
                )
            }
        }
    }

fun convertToCanvasCoordinates(
    county: County,
    canvasWidth: Float,
    canvasHeight: Float,
    scale: Float,
): List<List<Float>> {
    val minLongitude = Constants.MIN_LONGITUDE
    val maxLongitude = Constants.MAX_LONGITUDE
    val minLatitude = Constants.MIN_LATITUDE
    val maxLatitude = Constants.MAX_LATITUDE

    val scaleX = canvasWidth / (maxLongitude - minLongitude) * scale
    val scaleY = canvasHeight / (maxLatitude - minLatitude) * scale

    val offsetX = (canvasWidth - (maxLongitude - minLongitude) * scaleX) / 2
    val offsetY = (canvasHeight - (maxLatitude - minLatitude) * scaleY) / 2

    return county.coordinates[0].map { coordinate ->
        val x = (coordinate[0] - minLongitude) * scaleX + offsetX
        val y = (maxLatitude - coordinate[1]) * scaleY + offsetY
        listOf(x, y)
    }
}

// Create the counties
fun createCountyPath(
    county: County,
    canvasWidth: Float,
    canvasHeight: Float,
    scale: Float
): Path {
    return Path().apply {
        val coordinates = convertToCanvasCoordinates(county, canvasWidth, canvasHeight, scale)
        moveTo(coordinates[0][0], coordinates[0][1])
        coordinates.forEach { point ->
            lineTo(point[0], point[1])
        }
        close()
    }
}

// Check if pressed pixel is inside a county
fun isPointInsideCounty(path: Path, clickPosition: Offset): Boolean {
    val bounds = RectF()
    path.asAndroidPath().computeBounds(bounds, true)

    val androidRegion = Region().apply {
        val intBounds = Rect(
            bounds.left.toInt(),
            bounds.top.toInt(),
            bounds.right.toInt(),
            bounds.bottom.toInt()
        )
        setPath(path.asAndroidPath(), Region(intBounds))
    }

    return androidRegion.contains(clickPosition.x.toInt(), clickPosition.y.toInt())
}