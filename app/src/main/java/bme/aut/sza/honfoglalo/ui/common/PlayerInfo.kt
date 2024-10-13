package bme.aut.sza.honfoglalo.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PlayerInfo(
    name: String,
    score: Int,
    color: Color
){
    Column (
        //modifier = Modifier.fillMaxWidth(0.3f),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
                .padding(3.dp)
                .border(width = 3.dp, color = Color.Black, shape = CutCornerShape(10.dp),)
                .background(color, CutCornerShape(10.dp)),
            contentAlignment = Alignment.Center

        ){
            OutlinedText(
                text = score.toString(),
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fillColor = Color.White,
                outlineColor = Color.Black,
                outlineDrawStyle = Stroke(width = 7f)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(3.dp)
                .border(width = 3.dp, color = Color.Black, shape = CutCornerShape(10.dp),)
                .background(color, CutCornerShape(10.dp)),
            contentAlignment = Alignment.Center

        ){
            OutlinedText(
                text = name,
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fillColor = Color.White,
                outlineColor = Color.Black,
                outlineDrawStyle = Stroke(width = 7f)

            )
        }
    }

}

@Preview
@Composable
fun PlayerInfoPreview(){
    PlayerInfo("Lajos", 6900, Color.Red)
}