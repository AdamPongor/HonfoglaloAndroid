package bme.aut.sza.honfoglalo.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Question(question: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(3.dp)
            .border(width = 3.dp, color = Color.Black, shape = CutCornerShape(10.dp),),
        contentAlignment = Alignment.Center

    ){
        Text(
            text = question,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(align = Alignment.CenterVertically),
            textAlign = TextAlign.Center,
            fontSize = 20.sp


        )
    }
}

@Preview
@Composable
fun QuestionPreview(){
    Question("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut luctus erat dui, elementum venenatis mauris iaculis ac. Ut eget ultricies dolor. Aliquam id sapien in lacus ultrices varius ut at purus. Fusce tempor ligula eros, sed hendrerit risus suscipit dignissim.")
}