package bme.aut.sza.honfoglalo.util

import android.content.Context
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.RectF
import android.text.TextUtils
import android.util.DisplayMetrics
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ShapePath(private val pathData: String) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        return Outline.Generic(path = drawPath(size))
    }

    private fun drawPath(size: Size): Path {
        return Path().apply {
            reset()
            pathData.toPath(size, this)
            close()
        }
    }
}

fun String?.toPath(
    size: Size?,
    pathDestination: Path? = null,
): Path? {
    this?.let {
        size?.let {
            if (!TextUtils.isEmpty(this)) {
                val pathDestinationResult =
                    pathDestination ?: kotlin.run {
                        Path()
                    }
                val scaleMatrix = Matrix()
                val rectF = RectF()
                val path =
                    PathParser().parsePathString(this)
                        .toPath(pathDestinationResult)
                val bounds = path.getBounds()
                val rectPath = Rect(
                    bounds.left.toInt(),
                    bounds.top.toInt(),
                    bounds.right.toInt(),
                    bounds.bottom.toInt()
                )
                val scaleXFactor = size.width / rectPath.width().toFloat()
                val scaleYFactor = size.height / rectPath.height().toFloat()
                val androidPath = path.asAndroidPath()
                scaleMatrix.setScale(scaleXFactor, scaleYFactor, rectF.centerX(), rectF.centerY())
                androidPath.computeBounds(rectF, true)
                androidPath.transform(scaleMatrix)
                return androidPath.asComposePath()
            }
        }
    }
    return null
}

fun Float.pxToDp(context: Context): Float =
    (this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
