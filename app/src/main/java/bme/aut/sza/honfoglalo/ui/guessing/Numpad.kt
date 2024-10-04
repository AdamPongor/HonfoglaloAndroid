package bme.aut.sza.honfoglalo.ui.guessing

import android.widget.GridLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import bme.aut.sza.honfoglalo.R
import bme.aut.sza.honfoglalo.ui.common.AcceptButton
import bme.aut.sza.honfoglalo.ui.common.BackSpaceButton
import bme.aut.sza.honfoglalo.ui.common.NumButton

@Composable
fun Numpad() {

    var guess by remember { mutableStateOf("0") }

    Column()
    {
        Text(guess)

        Row(
            Modifier.weight(1f)
        ){
            for (i in 7..9){
                NumButton(
                    modifier = Modifier.padding(3.dp).weight(1f),
                    number = i,
                    onClick = {
                        guess = guess.plus(i)
                    }
                )
            }
            NumButton(
                modifier = Modifier.padding(3.dp).weight(1f),
                number = 0,
                onClick = {
                    guess = guess.plus(0)
                }
            )
            BackSpaceButton(
                modifier = Modifier.padding(3.dp).weight(1f),
                onClick = {
                    guess = guess.dropLast(1)
                }
            )
        }

        Row(
            Modifier.weight(2f)
        ){
            Column (
                Modifier.weight(3f)
            ){
                Row(
                    Modifier.fillMaxWidth().weight(1f)
                ){
                    for (i in 4..6){
                        NumButton(
                            modifier = Modifier.padding(3.dp).weight(1f),
                            number = i,
                            onClick = {
                                guess = guess.plus(i)
                            }
                        )
                    }
                }
                Row(
                    Modifier.fillMaxWidth().weight(1f)
                ){
                    for (i in 1..3){
                        NumButton(
                            modifier = Modifier.padding(3.dp).weight(1f),
                            number = i,
                            onClick = {
                                guess = guess.plus(i)
                            }
                        )
                    }
                }
            }
            AcceptButton (
                modifier = Modifier.padding(3.dp).weight(2f),
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
    Numpad()
}