package bme.aut.sza.honfoglalo.ui.guessing

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Popup
import bme.aut.sza.honfoglalo.data.Category
import bme.aut.sza.honfoglalo.data.Question
import bme.aut.sza.honfoglalo.ui.common.QuestionBox
import bme.aut.sza.honfoglalo.ui.map.HungaryMap
import bme.aut.sza.honfoglalo.ui.theme.Shade

@Composable
fun GuessingQuestion(
    question: Question,
    guess: MutableState<String>,
    onAcceptButtonClick: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
){

    var numpadScale: Float
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            numpadScale = 0.5f
        }
        else -> {
            numpadScale = 1f
        }
    }

    Popup(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Shade),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            QuestionBox(
                question = question.question,
                category = question.category
            )
            
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))

            Numpad(
                modifier = Modifier.fillMaxWidth(numpadScale),
                mutableState = guess,
                onAccept = onAcceptButtonClick
            )
        }
    }
}

@Preview
@Composable
fun GuessingQuestionPreview(){

    var guess = remember { mutableStateOf("") }

    val q = Question(
        question = "rnaőjgn őromfpweomfvjih lfvepvuvpfiei hngfyrfewísfwedfí feígbyvwrvyeberg yergihgb úp?",
        category = Category.ENTERTAINMENT,
        answers = listOf("69")
    )

    Box(modifier = Modifier){
        HungaryMap()
        GuessingQuestion(q, guess)
    }

}