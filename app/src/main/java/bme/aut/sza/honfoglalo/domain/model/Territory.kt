package bme.aut.sza.honfoglalo.domain.model

import androidx.compose.ui.graphics.Color
import bme.aut.sza.honfoglalo.data.entities.TerritoryEntity


data class Territory(
    val territory: String,
    val color: Color?
)

fun Territory.asTerritoryEntity(): TerritoryEntity {
    return TerritoryEntity.entries.find { it.FullName == territory }!!
}
