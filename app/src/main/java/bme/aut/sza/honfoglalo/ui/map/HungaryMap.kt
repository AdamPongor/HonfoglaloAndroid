package bme.aut.sza.honfoglalo.ui.map

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bme.aut.sza.honfoglalo.data.entities.Region
import bme.aut.sza.honfoglalo.ui.theme.Shade
import kotlin.random.Random

@Composable
fun HungaryMap() {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var xSize: Float
        var ySize: Float

        val configuration = LocalConfiguration.current
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                xSize = 0.69f
                ySize = 1f
            }

            else -> {
                xSize = 1f
                ySize = 0.29f
            }
        }

        BoxWithConstraints(
            modifier =
            Modifier
                .background(color = Color.Gray)
                .fillMaxWidth(xSize)
                .fillMaxHeight(ySize),
        ) {
            val y = maxHeight.value
            val x = maxWidth.value

            for (r: Region in Region.entries) {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(id = r.Resource),
                    tint = Color(
                        Random.nextInt(0, 255),
                        Random.nextInt(0, 255),
                        Random.nextInt(0, 255),
                        alpha = 100
                    ),
                    contentDescription = r.Name,
                )
            }
            RegionButton(x, y, arrayOf(2.3f, 2.6f), "Pest")
            RegionButton(x, y, arrayOf(2.2f, 5.1f), "Nógrád")
            RegionButton(x, y, arrayOf(1.85f, 4.1f), "Heves")
            RegionButton(x, y, arrayOf(1.55f, 7.5f), "B-A-Z")
            RegionButton(x, y, arrayOf(1.25f, 5.6f), "Sz-Sz-B")
            RegionButton(x, y, arrayOf(1.4f, 2.7f), "Hajdú- Bihar")
            RegionButton(x, y, arrayOf(1.53f, 1.75f), "Békés")
            RegionButton(x, y, arrayOf(1.8f, 1.5f), "Csongrád- Csanád")
            RegionButton(x, y, arrayOf(2.25f, 1.6f), "Bács- Kiskun")
            RegionButton(x, y, arrayOf(3.1f, 1.55f), "Tolna")
            RegionButton(x, y, arrayOf(3.5f, 1.25f), "Baranya")
            RegionButton(x, y, arrayOf(4.8f, 1.5f), "Somogy")
            RegionButton(x, y, arrayOf(9f, 1.7f), "Zala")
            RegionButton(x, y, arrayOf(11f, 2.3f), "Vas")
            RegionButton(x, y, arrayOf(6.5f, 3.5f), "Gy-M-S")
            RegionButton(x, y, arrayOf(3.4f, 3.3f), "Komárom- Esztergom")
            RegionButton(x, y, arrayOf(3.0f, 2.2f), "Fejér")
            RegionButton(x, y, arrayOf(4.6f, 2.2f), "Veszprém")
            RegionButton(x, y, arrayOf(1.7f, 2.4f), "J-N-K")
        }
    }
}

@Composable
fun RegionButton(
    x: Float,
    y: Float,
    offset: Array<Float>,
    name: String,
) {
    Button(
        modifier =
        Modifier
            .fillMaxHeight(0.10f)
            .fillMaxWidth(0.10f)
            .offset(x = (x / offset[0]).dp, y = (y / offset[1]).dp),
        onClick = { Log.d("xdd", name) },
        shape = ShapeDefaults.Small,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Shade),
    ) {
        Text(text = name, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
fun MapPreview() {
    HungaryMap()
}
