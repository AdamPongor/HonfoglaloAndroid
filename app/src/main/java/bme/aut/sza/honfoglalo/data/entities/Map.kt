package bme.aut.sza.honfoglalo.data.entities

import androidx.compose.ui.graphics.Color
import bme.aut.sza.honfoglalo.ui.theme.Tan

// Data classes for Map drawing from a GeoJson file
data class GeoJson(
    val type: String,
    val features: List<Feature>,
)

data class Feature(
    val type: String,
    val geometry: Geometry,
    val properties: Map<String, Any>?,
)

data class Geometry(
    val type: String,
    val coordinates: List<List<List<Float>>>,
)

// This is what we will use for drawing the map on the Canvas
data class County(
    val name: String,
    val coordinates: List<List<List<Float>>>,
    var color: Color = Tan,
)
