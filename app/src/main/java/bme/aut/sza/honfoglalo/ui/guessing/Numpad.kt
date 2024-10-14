package bme.aut.sza.honfoglalo.ui.guessing

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bme.aut.sza.honfoglalo.ui.common.AcceptButton
import bme.aut.sza.honfoglalo.ui.common.AutoResizeText
import bme.aut.sza.honfoglalo.ui.common.BackSpaceButton
import bme.aut.sza.honfoglalo.ui.common.FontSizeRange
import bme.aut.sza.honfoglalo.ui.common.NumButton
import bme.aut.sza.honfoglalo.ui.theme.Tan

@Composable
fun Numpad(
    modifier: Modifier = Modifier,
    mutableState: MutableState<String>,
    onAccept: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 40.dp, vertical = 3.dp)
                    .border(width = 3.dp, color = Color.Black, shape = CutCornerShape(10.dp))
                    .clip(CutCornerShape(10.dp))
                    .background(color = Tan),
            contentAlignment = Alignment.Center,
        ) {
            AutoResizeText(
                text = mutableState.value,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                fontSizeRange = FontSizeRange(15.sp, 30.sp),
            )
        }

        Row {
            for (i in 7..9) {
                NumButton(
                    modifier =
                        Modifier
                            .padding(3.dp)
                            .weight(1f),
                    number = i,
                    onClick = {
                        mutableState.value = mutableState.value.plus(i)
                    },
                )
            }
            NumButton(
                modifier =
                    Modifier
                        .padding(3.dp)
                        .weight(1f),
                number = 0,
                onClick = {
                    mutableState.value = mutableState.value.plus(0)
                },
            )
            BackSpaceButton(
                modifier =
                    Modifier
                        .padding(3.dp)
                        .weight(1f),
                onClick = {
                    mutableState.value = mutableState.value.dropLast(1)
                },
            )
        }

        Row {
            Column(
                Modifier.weight(3f),
            ) {
                Row(
                    Modifier.fillMaxWidth(), // .weight(1f)
                ) {
                    for (i in 4..6) {
                        NumButton(
                            modifier =
                                Modifier
                                    .padding(3.dp)
                                    .weight(1f),
                            number = i,
                            onClick = {
                                mutableState.value = mutableState.value.plus(i)
                            },
                        )
                    }
                }
                Row(
                    Modifier.fillMaxWidth(), // .weight(1f)
                ) {
                    for (i in 1..3) {
                        NumButton(
                            modifier =
                                Modifier
                                    .padding(3.dp)
                                    .weight(1f),
                            number = i,
                            onClick = {
                                mutableState.value = mutableState.value.plus(i)
                            },
                        )
                    }
                }
            }
            AcceptButton(
                modifier =
                    Modifier
                        .padding(3.dp)
                        .weight(2f)
                        .height(106.dp),
                onClick = {
                },
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

    Numpad(
        mutableState = guess,
    )
}
