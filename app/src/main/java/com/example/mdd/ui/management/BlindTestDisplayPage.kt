package com.example.mdd.ui.management

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mdd.viewmodel.BlindTestViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.mdd.R
import com.example.mdd.model.DataBlindTest
import com.example.mdd.navigations.Screen
import com.example.mdd.ui.theme.MDDTheme

@SuppressLint("RememberReturnType")
@Composable
fun BlindTestScreenDisplay(navController: NavController,viewModel: BlindTestViewModel){
    val context = LocalContext.current
    viewModel.loadBlindTestView(context)
    val currentQuestion by viewModel.currentQuestion.collectAsState()
    /*viewModel.loadBlindTest(context)
    val blindTest by viewModel.blindTest.collectAsState()
    val questions = remember(blindTest) {
        blindTest?.questions ?: emptyList()}
    viewModel.loadBlindTest(context)*/
    val blindTest by viewModel.blindTest.collectAsState()
    val genres = remember(blindTest) {
        blindTest?.questions?.map { it.genre }?.distinct() ?: emptyList()}
    Column ( modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally){
    Text(text = "Bienvenue dans le BlindTest")
    //Spacer(modifier = Modifier.weight(0.5f))
    // La question  change selon les paramètres
    Text(text = "Quel est la chanson ?")
    Image(painterResource(R.drawable.baseline_library_music_24), contentDescription = "Image musique")
        LazyColumn {
            items(genres) { genre ->
                Button(onClick = {
                    viewModel.selectGenre(genre)
                    navController.navigate(Screen.Question.route)
                }) {
                    Text(text = genre)
                }
            }
        }
        Text(text = "NON")
    }
}



@Composable
fun StartScreen(navController: NavController, viewModel: BlindTestViewModel){
    val context = LocalContext.current
    viewModel.loadBlindTestView(context)
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            viewModel.startBlindTest()
            navController.navigate(Screen.Home.route)
        }) {

        }
    }

@Composable
fun QuestionScreen(navController: NavHostController, viewModel: BlindTestViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Question Screen")
    }
}

@Composable
fun GenreSelectionScreen(navController: NavHostController, viewModel: BlindTestViewModel) {
    viewModel.loadBlindTestView(context)
    val blindTest by viewModel.blindTest.collectAsState()
    val genres = remember(blindTest) {
        blindTest?.questions?.map { it.genre }?.distinct() ?: emptyList()
    }

    LazyColumn {
        items(genres) { genre ->
            Button(onClick = {
                viewModel.selectGenre(genre)
                navController.navigate(Screen.Question.route)
            }) {
                Text(text = genre)
            }
        }
    }
}

@Composable
fun ArtistSelectionScreen(navController: NavHostController, viewModel: BlindTestViewModel) {
    viewModel.loadBlindTestView(context)
    val blindTest by viewModel.blindTest.collectAsState()
    val artists = remember(blindTest) {
        blindTest?.questions?.map { it.genre }?.distinct() ?: emptyList()
    }

    LazyColumn {
        items(artists) { artist ->
            Button(onClick = {
                viewModel.selectGenre(artist)
            }) {
                Text(text = artist)
            }
        }
    }
}


    /*val blindTest by viewModel.blindTest.collectAsState()*/
    /*val genres = blindTest?.questions?.map { it.genre }?.distinct() ?: emptyList()
    val artists = blindTest?.questions?.map { it.artiste }?.distinct() ?: emptyList()*/

}

@Preview(showBackground = true)
@Composable
fun BlindTest() {
    MDDTheme {
        Column {
           BlindTestScreenDisplay(
               navController = NavController(context = LocalContext.current),
               viewModel = BlindTestViewModel()
           )
            //GenreListScreen()
        }
    }
}
