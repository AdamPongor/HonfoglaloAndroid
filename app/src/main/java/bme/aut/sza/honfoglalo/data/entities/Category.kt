package bme.aut.sza.honfoglalo.data.entities

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import bme.aut.sza.honfoglalo.R

enum class Category(val category: String, val icon: Int) {
    ART("ART", R.drawable.art),
    EVERYDAY("EVERYDAY", R.drawable.everyday),
    GEOGRAPHY("GEOGRAPHY", R.drawable.geography),
    HISTORY("HISTORY", R.drawable.history),
    LITERATURE("LITERATURE", R.drawable.literature),
    MATHS_PHYSICS("MATHS_PHYSICS", R.drawable.maths),
    BIOLOGY_CHEMISTRY("BIOLOGY_CHEMISTRY", R.drawable.biology),
    SPORTS("SPORTS", R.drawable.sports),
    ENTERTAINMENT("ENTERTAINMENT", R.drawable.entertainment),
    LIFESTYLE("LIFESTYLE", R.drawable.lifestyle),
    ;

    companion object {
        fun fromString(category: String): Category {
            return entries.find { it.category.equals(category, ignoreCase = true) }!!
        }
    }

    @Composable
    fun getIcon(): Painter {
        return painterResource(icon)
    }
}

