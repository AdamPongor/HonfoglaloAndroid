package bme.aut.sza.honfoglalo.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import bme.aut.sza.honfoglalo.R

@Composable
fun NumButton(
    modifier: Modifier = Modifier,
    number: Int,
    backgroundColor: Color = Color.Gray,
    onClick: () -> Unit
){
    TextButton(
        modifier = modifier.size(50.dp),
        onClick = onClick,
        shape = CutCornerShape(10.dp),
        border = BorderStroke(color = Color.Black, width = 3.dp),
        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = backgroundColor)
    ){
        Text(number.toString())
    }
}

@Composable
fun BackSpaceButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Red,
    onClick: () -> Unit
){
    TextButton(
        modifier = modifier.size(50.dp),
        onClick = onClick,
        shape = CutCornerShape(10.dp),
        border = BorderStroke(color = Color.Black, width = 3.dp),
        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = backgroundColor)
    ){
        Icon(painter = painterResource(R.drawable.baseline_backspace_24), "backspace")
    }
}

@Composable
fun AcceptButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Green,
    onClick: () -> Unit
){
    TextButton(
        modifier = modifier.size(100.dp),
        onClick = onClick,
        shape = CutCornerShape(10.dp),
        border = BorderStroke(color = Color.Black, width = 3.dp),
        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = backgroundColor)
    ){
        Icon(painter = painterResource(R.drawable.baseline_play_arrow_24), "accept", modifier = Modifier.fillMaxSize())
    }
}