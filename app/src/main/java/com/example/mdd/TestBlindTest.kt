

package com.example.mdd

import android.annotation.SuppressLint

import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mdd.viewmodel.BlindTestViewModel
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.stringResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mdd.model.DataBlindTestAnwser
import com.example.mdd.ui.theme.MDDTheme

import com.example.mdd.utils.BlindTestQuestionsJsonUtils.loadQuestions


@SuppressLint("CustomSplashScreen")
class TestBlindTest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val viewModel: BlindTestViewModel = viewModel()
           StartScreenBlindTest(
                viewModel = viewModel
            )

            }
        }
    }



@Composable
fun StartScreenBlindTest(viewModel: BlindTestViewModel) {
    val context = LocalContext.current
    viewModel.loadBlindTestView(context = context)
    var score = 0
    val timerValue by viewModel.timer.collectAsState()
    val isCorrectAnswer by viewModel.isCorrectAnswer.collectAsState()
    val formatedTime = formatTime(timerValue)

    val questions = loadQuestions(context)
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var showNextQuestionButton by remember { mutableStateOf(false) }
    val maxQuestions = 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenue dans le BlindTest")
        //Spacer(modifier = Modifier.weight(0.5f))
        // La question  change selon les paramètres

        Text(text = "Score: $score")
        Text(text = timerValue.toString())
        Text(text = "Quel est la chanson ?")
        if (currentQuestionIndex < questions.size && currentQuestionIndex < maxQuestions) {
            val currentQuestions = questions[currentQuestionIndex]
            val shuffledAnswers = remember(currentQuestions) {
                currentQuestions.answers.shuffled()
            }

            if (!showNextQuestionButton) {
                // Shuffle the answers before displaying them

                DisplayAnswers(shuffledAnswers, viewModel) {
                    showNextQuestionButton = true
                }

            } else {
                Button(onClick = {
                    showNextQuestionButton = false
                    currentQuestionIndex++
                }) {
                    Text(text = stringResource(R.string.blind_start))
                }
            }
            if (isCorrectAnswer != null) {
                if (isCorrectAnswer == true) {
                    Text(text = "Correct!")
                    score.plus(15)
                    currentQuestionIndex++
                    showNextQuestionButton = true
                } else {
                    Text(text = "Incorrect!")
                    score = score.minus(6)
                    currentQuestionIndex++
                    showNextQuestionButton = true
                }
            }
        } else {
            Text(text = "No more questions")
        }

        }

    }


fun formatTime(totalSecond: Int): String {
    val minutes = totalSecond / 60
    val seconds = totalSecond % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Composable
fun DisplayAnswers(answers: List<DataBlindTestAnwser>, viewModel: BlindTestViewModel,onAnswerSelected: () -> Unit ) {
    Column {
        answers.forEach { answer -> // Changed from items() to forEach()
            Button(onClick = {
                viewModel.checkAnswer(answer)
                Log.d("BlindTest", "Answer selected: ${answer.isCorrect}")
                onAnswerSelected()
                //viewModel.nextQuestion()
            }) {
                Text(text = answer.text)
            }
            Spacer(modifier = Modifier.padding(4.dp)) // Add space between buttons
        }
    }
    }




@Preview(showBackground = true)
@Composable
fun BlindTest2() {
    MDDTheme {
        Column {
           /* BlindTestScreenDisplay(
                navController = NavController(context = LocalContext.current),
                viewModel = BlindTestViewModel()
            )*/
            StartScreenBlindTest(
               viewModel = viewModel()
            )
            //GenreListScreen()
        }
    }
}
