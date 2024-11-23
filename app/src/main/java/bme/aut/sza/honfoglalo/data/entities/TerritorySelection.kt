package bme.aut.sza.honfoglalo.data.entities

data class TerritorySelections(
    val territorySelection: List<TerritorySelection>
)

data class TerritorySelection(
    val id: String,
    val selections: Int
)
