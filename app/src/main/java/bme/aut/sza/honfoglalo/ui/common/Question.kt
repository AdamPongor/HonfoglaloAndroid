package bme.aut.sza.honfoglalo.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bme.aut.sza.honfoglalo.data.Category
import bme.aut.sza.honfoglalo.ui.theme.Tan

@Composable
fun QuestionBox(question: String, category: Category){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
            .padding(3.dp)
            .border(width = 3.dp, color = Color.Black, shape = CutCornerShape(10.dp))
            .clip(CutCornerShape(10.dp))
            .background(color = Tan),
        contentAlignment = Alignment.Center,

    ){
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(id = category.icon),
                category.string.toString(),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 5.dp)
            )
            AutoResizeText(
                text = question,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .weight(8f),
                textAlign = TextAlign.Center,
                fontSizeRange = FontSizeRange(5.sp, 25.sp)


            )
            Spacer(modifier = Modifier.weight(1f).padding(end = 5.dp))
        }
    }
}

@Preview
@Composable
fun QuestionPreview(){
    QuestionBox(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut luctus erat dui, elementum venenatis mauris iaculis ac. Ut eget ultricies dolor. Aliquam id sapien in lacus ultrices varius ut at purus. Fusce tempor ligula eros, sed hendrerit risus suscipit dignissim.",
        Category.BIOLOGY_CHEMISTRY
        )
}