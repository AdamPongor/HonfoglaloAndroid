package bme.aut.sza.honfoglalo.ui.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import bme.aut.sza.honfoglalo.R

@Composable
fun WaitHourglass(waitMessage: String){
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")

    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 180F,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, delayMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ), label = "angle"
    )

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Icon(
            painter = painterResource(id = R.drawable.round_hourglass_bottom_24),
            contentDescription = "loading",
            modifier = Modifier.rotate(angle)
        )
        Text(text = waitMessage)
    }
}

@Preview
@Composable
fun WaitHourglassPreview(){
    WaitHourglass("mindenki nyugodjon le a gecibe")
}