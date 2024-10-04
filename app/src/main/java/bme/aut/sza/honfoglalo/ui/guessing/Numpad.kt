package bme.aut.sza.honfoglalo.ui.guessing

import android.widget.GridLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bme.aut.sza.honfoglalo.R
import bme.aut.sza.honfoglalo.ui.common.AcceptButton
import bme.aut.sza.honfoglalo.ui.common.BackSpaceButton
import bme.aut.sza.honfoglalo.ui.common.NumButton

@Composable
fun Numpad(mutableState: MutableState<String>, onAccept: () -> Unit) {

    Column(
        Modifier.fillMaxWidth(1f),
        verticalArrangement = Arrangement.Top
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp, vertical = 3.dp)
                .border(width = 3.dp, color = Color.Black, shape = CutCornerShape(10.dp),),
            contentAlignment = Alignment.Center

        ){
            Text(
                text = mutableState.value,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 20.sp


            )
        }


        Row(
            //Modifier.weight(1f)
        ){
            for (i in 7..9){
                NumButton(
                    modifier = Modifier
                        .padding(3.dp)
                        .weight(1f),
                    number = i,
                    onClick = {
                        mutableState.value = mutableState.value.plus(i)
                    }
                )
            }
            NumButton(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(1f),
                number = 0,
                onClick = {
                    mutableState.value = mutableState.value.plus(0)
                }
            )
            BackSpaceButton(
                modifier = Modifier
                    .padding(3.dp)
                    .weight(1f),
                onClick = {
                    mutableState.value = mutableState.value.dropLast(1)
                }
            )
        }

        Row(
            //Modifier.weight(2f)
        ){
            Column (
                Modifier.weight(3f)
            ){
                Row(
                    Modifier.fillMaxWidth()//.weight(1f)
                ){
                    for (i in 4..6){
                        NumButton(
                            modifier = Modifier
                                .padding(3.dp)
                                .weight(1f),
                            number = i,
                            onClick = {
                                mutableState.value = mutableState.value.plus(i)
                            }
                        )
                    }
                }
                Row(
                    Modifier.fillMaxWidth()//.weight(1f)
                ){
                    for (i in 1..3){
                        NumButton(
                            modifier = Modifier
                                .padding(3.dp)
                                .weight(1f),
                            number = i,
                            onClick = {
                                mutableState.value = mutableState.value.plus(i)
                            }
                        )
                    }
                }
            }
            AcceptButton (
                modifier = Modifier
                    .padding(3.dp)
                    .weight(2f)
                    .height(106.dp),
                onClick = {
                }
            )
        }

        /*LazyVerticalGrid(
            modifier = Modifier.weight(1f),
            columns = GridCells.Fixed(3),
            reverseLayout = true,
        ) {
            items(9) { item ->
                NumButton(
                    modifier = Modifier.padding(3.dp),
                    number = item + 1,
                    onClick = {
                        guess = guess.plus(item+1)
                    }
                )
            }
            item {
                NumButton(
                    modifier = Modifier.padding(3.dp),
                    number = 0,
                    onClick = {
                        guess = guess.plus(0)
                    }
                )
            }
        }*/
    }
}

@Preview
@Composable
fun NumpadPreview() {

    var guess = remember { mutableStateOf("") }

    Numpad(guess, {})
}