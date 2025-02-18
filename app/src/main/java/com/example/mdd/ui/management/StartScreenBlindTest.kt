package com.example.mdd.ui.management

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.mdd.navigations.Screen
import com.example.mdd.viewmodel.BlindTestViewModel

@Composable
fun StartScreenBlindTest(navController: NavHostController, viewModel: BlindTestViewModel) {
    val context = LocalContext.current
    viewModel.loadBlindTestView(context)
    val blindTest by viewModel.blindTest.collectAsState()
    val questions = remember (blindTest){
        blindTest?.questions ?: emptyList()
    }
    Log.d("StarScreenBlindTest", "Nombre de question: ${questions.size}")

    LazyColumn {
        items(questions){ question ->
            Button(onClick = {
                viewModel.startBlindTestWithQuestion(question)
                navController.navigate(Screen.Question.route)
            } ) { Text("Boutton 1") }

        }
    }
}