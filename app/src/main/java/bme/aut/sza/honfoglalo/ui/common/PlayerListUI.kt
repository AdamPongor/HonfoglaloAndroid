package bme.aut.sza.honfoglalo.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bme.aut.sza.honfoglalo.R
import bme.aut.sza.honfoglalo.ui.theme.FlatCornerShape

@Composable
fun PlayerListUI(color: Color, name: String){
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        border = BorderStroke(2.dp, Color.Black),
        shape = FlatCornerShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ){
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp),
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = R.drawable.outline_person_24),
                contentDescription = "player"
            )
            Text(
                text = name,
                modifier = Modifier
                    .padding(16.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )
            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .aspectRatio(1f)
                    .fillMaxHeight()
                    .width(40.dp)
                    .background(color = color, shape = FlatCornerShape)
                    .clip(FlatCornerShape)
                    .border(BorderStroke(2.dp, Color.Black), shape = FlatCornerShape)
            )
        }
    }
}


@Preview
@Composable
fun PlayerListElementPreview(){
    PlayerListUI(Color.Blue, "Jeff xd")
}