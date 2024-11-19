package bme.aut.sza.honfoglalo.data.entities

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import bme.aut.sza.honfoglalo.R

enum class Category(val string: Int, val icon: Int) {
    ART(R.string.art, R.drawable.art),
    EVERYDAY(R.string.everyday, R.drawable.everyday),
    GEOGRAPHY(R.string.geography, R.drawable.geography),
    HISTORY(R.string.history, R.drawable.history),
    LITERATURE(R.string.literature, R.drawable.literature),
    MATHS_PHYSICS(R.string.maths, R.drawable.maths),
    BIOLOGY_CHEMISTRY(R.string.biology, R.drawable.biology),
    SPORTS(R.string.sports, R.drawable.sports),
    ENTERTAINMENT(R.string.entertainment, R.drawable.entertainment),
    LIFESTYLE(R.string.lifestyle, R.drawable.lifestyle),
    ;

    fun getString(c: Category): String {
        return c.string.toString()
    }

    @Composable
    fun getIcon(c: Category): Painter {
        return painterResource(c.icon)
    }
}
