package bme.aut.sza.honfoglalo.domain.model

import bme.aut.sza.honfoglalo.data.entities.TerritoryEntity


data class Territory(
    val territory: String
)

fun Territory.asTerritoryEntity(): TerritoryEntity {
    return TerritoryEntity.entries.find { it.FullName == territory }!!
}
