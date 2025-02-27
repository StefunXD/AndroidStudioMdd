package com.example.mdd



import android.annotation.SuppressLint
import android.app.Application
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth


import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


import androidx.navigation.compose.rememberNavController
import com.example.mdd.model.DataFakeSimilar


import com.example.mdd.navigations.Screen
import com.example.mdd.ui.elements.DisplaySimilarMusicData

import com.example.mdd.ui.management.Blindtestmanagerpage
import com.example.mdd.ui.management.SimilarMusicScreenPreview
import com.example.mdd.ui.theme.MDDTheme



import com.example.mdd.viewmodel.SimilarMusicViewModel
import com.example.mdd.viewmodel.SliderViewModel


class MainActivity : ComponentActivity() {

    private val similarMusicViewModel: SimilarMusicViewModel by viewModels()
    var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MDDTheme {
                mediaPlayer = MediaPlayer.create(this, R.raw.theelevatorbossanova)
                mediaPlayer?.isLooping = true

                // Démarrer la lecture
                mediaPlayer?.start()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  MainPage(SampleSimilarMainPage.SimilarMainPageSample)
                    //Graph(navController = navController, viewModel = viewModel)
                }
            }
        }
        fun onDestroy() {
            super.onDestroy()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}

data class SimilarMainPage(val user_name:String, val song_name_1 :String,
                           val album_name_1:String, val artist_name_1: String,
                           val song_date_1 : String, val similar:String,
                           val song_name_2: String, val album_name_2: String,
                           val artist_name_2: String, val song_date_2: String)

object SampleSimilarMainPage {
    val SimilarMainPageSample = listOf(SimilarMainPage("User 01343EEZA341Z","Outta my head","Pacific Time 2","Phonte et Carmen Rodgers","2025","Motif musical","Crust","Yasuke","Flying Lotus","2021"),
        SimilarMainPage("User 725522dze5Z","Nakie Nakie","NEXT","Electro Deluxe","2024","Sonorité, rythme","Wort it to the top","Tales From The Land Of Milk And Honey","The Foreign Exchange","2015"),
        SimilarMainPage("User 41343ppEZA3658","Hardrock","Sound System","Herbie Hancock","2024","Sonorité","Rockit","Futur Shock","Herbie Hancock","1983"))
}

@Composable
fun MainPage(smps: List<SimilarMainPage>, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var isPlaying by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    //val genreList = loadGenres(context)
    Row( modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(text = stringResource(R.string.main_page_new))
        IconButton(onClick = {
                navController.navigate(Screen.LoginScreen.route)
        }) {
            Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Connexion")
        }
    }

    Text(text = stringResource(R.string.main_page_question))
    Column {
        LazyColumn {
            items(smps) { smp -> CardSimilarMainPage(smp, modifier) }
        }

        Button(onClick = { /*TODO*/ },  modifier = Modifier.align(Alignment.CenterHorizontally)) {

            Text(text = "Affichez plus")
        }
    }
}

@Composable
fun CardSimilarMainPage(smp: SimilarMainPage, modifier: Modifier) {
    Card {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.sharp_android_24),
                contentDescription = "Utilisateur",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("User : \"${smp.user_name}\"       20 m")
        }
        Column {
            Text(
                //si groupe ok on change la phrase, il faut des données de défaults, changer la phrase des similaire par rapport à l'attribut
                text = "La chanson \"${smp.song_name_1}\" de l'album ${smp.album_name_1} de l'artiste ${smp.album_name_1} sortie en ${smp.song_date_1} " +
                        "est similaire à \"${smp.song_name_2}\", de l'album " +
                        "${smp.album_name_2} de l'artiste ${smp.artist_name_2} sortie en" +
                        " ${smp.song_date_2}",
                modifier = modifier
            )
            Text(smp.similar, modifier = Modifier.align(Alignment.CenterHorizontally))
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.baseline_thumb_up_alt_24),
                    contentDescription = "Like button"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.baseline_comment_24),
                    contentDescription = "Like button"
                )

            }
        }
    }
}





       /* Row() {
            Image(
                painter = painterResource(id = R.drawable.sharp_android_24),
                contentDescription = "Utilisateur",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("User : \"${smp.user_name}\"       20 m")
        }
        Column {
            Text(
                //si groupe ok on change la phrase, il faut des données de défaults, changer la phrase des similaire par rapport à l'attribut
                text = "La chanson \"${smp.song_name_1}\" de l'album ${smp.album_name_1} de l'artiste ${smp.album_name_1} sortie en ${smp.song_date_1} " +
                        "est similaire à \"${smp.song_name_2}\", de l'album " +
                        "${smp.album_name_2} de l'artiste ${smp.artist_name_2} sortie en" +
                        " ${smp.song_date_2}",
                modifier = modifier
            )
            Text(smp.similar, modifier = Modifier.align(Alignment.CenterHorizontally))
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.baseline_thumb_up_alt_24),
                    contentDescription = "Like button"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.baseline_comment_24),
                    contentDescription = "Like button"
                )

            }
        }
    }*/




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SimilarFullScreen(similarMusicViewModel: SimilarMusicViewModel) {
    val context = LocalContext.current
    val similarMusicList by similarMusicViewModel.similarMusicList.collectAsState()
    val isLoading by similarMusicViewModel.isLoading.collectAsState()
    val error by similarMusicViewModel.error.collectAsState()

    LaunchedEffect(key1 = true) {
        similarMusicViewModel.loadSimilarMusicData(context)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text(text = "Error: $error")
        } else {
            if (similarMusicList.isNotEmpty()) {
                DisplaySimilarMusicData(similarMusicList)
            } else {
                Text(text = "No data available")
            }
        }
    }
}

///User


///BlindTests

/*@Composable
fun SimilarMusicPreview() {
    val viewModel: FakeSimilarViewModel = viewModel(factory = FakeSimilarViewModelFactory(LocalContext.current.applicationContext as Application))
    val similarMusicList by viewModel.similarMusicList.observeAsState(initial = emptyList())

    LazyColumn {
        items(similarMusicList) { music ->
            CardSimilarMainPage(music = music)
        }
    }
}*/
@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun Finale_blind_test_assembler() {
    Blindtestmanagerpage(viewModelslider = SliderViewModel())
    val viewModelslider = SliderViewModel()
    Text(text = "Il y'a auras" + viewModelslider.sliderValue.toString() + " questions")
}

@Composable
fun CardSimilarMainPage(music: DataFakeSimilar, modifier: Modifier = Modifier) {
    Card {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.sharp_android_24),
                    contentDescription = "Utilisateur",
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("User : \"${music.username}\"")
            }
            Text(
                text = "La chanson \"${music.song1}\" de l'album ${music.album1} de l'artiste ${music.artist1} est similaire à \"${music.song2}\", de l'album ${music.album2} de l'artiste ${music.artist2}",
                modifier = modifier
            )
            Text(music.similar, modifier = Modifier.align(Alignment.CenterHorizontally))
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.baseline_thumb_up_alt_24),
                    contentDescription = "Like button"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.baseline_comment_24),
                    contentDescription = "Like button"
                )
            }
        }
    }
}




@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun Finale_Similar_assembler(){
     Blindtestmanagerpage(viewModelslider = SliderViewModel())
    val viewModelslider = SliderViewModel()
    Text(text = "Il y'a auras" + viewModelslider.sliderValue.toString() +" questions")
}


@SuppressLint("ViewModelConstructorInComposable")
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreviewGreetings() {
    MDDTheme {
        Column {
            SimilarMusicScreenPreview()
          //MainPage(SampleSimilarMainPage.SimilarMainPageSample)

            //GenreListScreen()
            //SimilarFullScreen(similarMusicViewModel = viewModel())
        }

    }
}

@Preview(showBackground = true)
@Composable
fun BlindTest() {
    MDDTheme {
        Column {
            Finale_blind_test_assembler()
            //GenreListScreen()
        }
    }
}
