package com.example.mdd

import android.annotation.SuppressLint

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mdd.ui.elements.SliderDiscrete
import com.example.mdd.ui.theme.MDDTheme
import com.example.mdd.uimanager.DropDownOptionsBlindTest
import com.example.mdd.uimanager.GenreRaw
import com.example.mdd.utils.loadGenres
import com.example.mdd.viewmodel.ViewModelBlindTestOptionsDropdown
import androidx.compose.material3.SegmentedButtonDefaults.itemShape as itemShape1

class MainActivity : ComponentActivity() {
    var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MDDTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding))
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


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun PageLogo(quts: List<QuoteLogoPage>, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    Column() {
        Image(
            painter = painterResource(id = R.drawable.baseline_library_music_24),
            contentDescription = "Utilisateur",
        )
        Text("Bienvenue sur MDD")
        LazyColumn {
            items(quts) { quote -> Text(quote.quote) }
        }
    }
}


@Composable
fun VersionZeroPageLogo(image_logo: String, quote:String, modifier: Modifier = Modifier) {
    Card () {
        Text("Bienvenue sur MDD")
        Text(image_logo)
        Text(quote)
    }
}

data class QuoteLogoPage(val quote: String,val song: String, val artist: String,
                         val album: String, val date: String)
object SampleQuoteLogoPage {
    val Quotes = listOf(QuoteLogoPage("I found a diamond in the rough","Diamond in da Ruff", "Matt Martians", "The Drum Chord Theory", "2017"),
        QuoteLogoPage("Groove is in the Heart","Groove is In the Hearts", "Deee-Lite", "The elektra Years", "1990"),
        QuoteLogoPage("The way you move is mystery", "D.A.N.C.E", "Justice", "Justice","2007"),
        QuoteLogoPage("Tonight she just yelling, \"Fuck me\", two weeks she'll be yelling fuck me", "CocoaButter Kisses", "Chance the Rapper", "Acid Rap","2013"),
        QuoteLogoPage("Go! Go! Go! Godzilla", "Godzilla", "Bear McCreary, Serj Takian", "Godzilla King of The Monsters","2013"))
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
    val genreList = loadGenres(context)
    Text(text = "Nouveautés")
    Text(text = "Questions")
    Column {
        LazyColumn {
            items(smps) { smp -> CardSimilarMainPage(smp, modifier) }
        }
        GenreRaw()
        Button(onClick = { /*TODO*/ },  modifier = Modifier.align(Alignment.CenterHorizontally)) {

            Text(text = "Affichez plus")
        }
    }
}

@Composable
fun CardSimilarMainPage(smp: SimilarMainPage, modifier: Modifier = Modifier) {
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

///User


///BlindTests


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun blind_test_manager_page(){
    ///Par genre
    ///Par artist
    ///Par niveau de difficulté
    ///Nombres de questions
    val context = LocalContext.current
    //actions
    //Dropdown
    val viewModel : ViewModelBlindTestOptionsDropdown = viewModel()
    var selectedValueFromDropdown by remember { mutableStateOf("")}
    //Difficulté
    var selecteddiff by remember { mutableStateOf(0) }
    val options_segmentedbutton = listOf(context.getString(R.string.bt_diff1),context.getString(R.string.bt_diff2))

    Column (modifier = Modifier.padding(16.dp)) {
        Text(text = "Blind test musical")
        Text(text = "Choississez les options de votre blind test")
        DropDownOptionsBlindTest(viewModel = viewModel,
            onOptionSelected = { selectedOption -> selectedValueFromDropdown = selectedOption})
        //$selectedOption
        Text(text = context.getString(R.string.bt_diff), modifier = Modifier.align(Alignment.CenterHorizontally))
        SingleChoiceSegmentedButtonRow ( modifier = Modifier.align(Alignment.CenterHorizontally)) {
            options_segmentedbutton.forEachIndexed {index, label -> SegmentedButton(
                shape = itemShape1(index = index, count = options_segmentedbutton.size),
                onClick = { selecteddiff = index},
               //valeur que je récupère
                selected = index == selecteddiff
            ) {
                Text(label)
            }
            }
        }
        Text(text = context.getString(R.string.dropdown_bt_many),modifier = Modifier.align(Alignment.CenterHorizontally))
        SliderDiscrete()

        Button(onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = context.getString(R.string.blind_start))
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
private fun ExposedDropdownMenuBoxScope.OutlinedTextField(
    value: String,
    onValueChange: () -> Unit,
    readOnly: Boolean,
    trailingIcon: @Composable () -> Unit,
    function: () -> Unit
) {
    TODO("Not yet implemented")
}

@Composable
fun blind_test_question_page(){

}

@Composable
fun Finale_blind_test_assembler(){
blind_test_manager_page()
}


//@Composable
//fun VersionZeroSongtoSongSimilar(song_name_1 :String, album_name_1:String, artist_name_1: String, song_date_1 : String, similar_1:String,
//                       song_name_2 :String, album_name_2 :String, artist_name_2: String, song_date_2 : String,
//                       song_name_3 :String, album_name_3:String, artist_name_3: String, song_date_3 : String, similar_2:String,
//                       song_name_4 :String, album_name_4 :String, artist_name_4: String, song_date_4 : String,
//                       song_name_5 :String, album_name_5 :String, artist_name_5: String, song_date_5 : String, similar_3:String,
//                       song_name_6 :String, album_name_6 :String, artist_name_6: String, song_date_6 : String,
//                       modifier: Modifier = Modifier) {
//    //apprendre les boucles
//    Card {
//        Row() {
//            Image(
//                painter = painterResource(id = R.drawable.sharp_android_24),
//                contentDescription = "Utilisateur",
//                modifier = Modifier
//                    .size(30.dp)
//                    .clip(CircleShape)
//
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text("Name user 01343EEZA341Z")
//        }
//        Column {
//            Text(
//                //si groupe ok on change la phrase, il faut des données de défaults, changer la phrase des similaire par rapport à l'attribut
//                text = "La chanson \"$song_name_1\" de l'album $album_name_1 de l'artiste $artist_name_1 qui dure $song_date_1 est similaire à \"$song_name_2\", de l'album $album_name_2 de l'artiste $artist_name_2 qui dure $song_date_2",
//                modifier = modifier
//            )
//            Text(similar_1)
//            Row(){
//            Image(
//                painter = painterResource(id = R.drawable.baseline_thumb_up_alt_24),
//                contentDescription = "Like button"
//            )
//                Spacer(modifier = Modifier.width(20.dp))
//            Image(
//                painter = painterResource(id = R.drawable.baseline_comment_24),
//                contentDescription = "Like button"
//                )
//            }
//        }
//    }
//    Card {
//        Text(
//            //si groupe ok on change la phrase, il faut des données de défaults, changer la phrase des similaire par rapport à l'attribut
//            text = "La chanson \"$song_name_3\" de l'album $album_name_3 de l'artiste $artist_name_3 sortie en $song_date_3 est similaire à \"$song_name_4\", de l'album $album_name_4 de l'artiste $artist_name_4 qui dure $song_date_4",
//            modifier = modifier
//        )
//        Text(similar_2)
//    }
//    Card {
//        Text(
//            //si groupe ok on change la phrase, il faut des données de défaults, changer la phrase des similaire par rapport à l'attribut
//            text = "La chanson \"$song_name_3\" de l'album $album_name_3 de l'artiste $artist_name_3 sortie en $song_date_3 est similaire à \"$song_name_4\", de l'album $album_name_4 de l'artiste $artist_name_4 qui dure $song_date_4",
//            modifier = modifier
//        )
//        Text(similar_2)
//    }
//    Card {
//        Text(
//            //si groupe ok on change la phrase, il faut des données de défaults, changer la phrase des similaire par rapport à l'attribut
//            text = "La chanson \"$song_name_5\" de l'album $album_name_5 de l'artiste $artist_name_5 sortie en $song_date_5 est similaire à \"$song_name_6\", de l'album $album_name_6 de l'artiste $artist_name_6 qui dure $song_date_6",
//            modifier = modifier
//        )
//        Text(similar_3)
//        Button(onClick = { /*TODO*/ }) {
//            Text(text = "Affichez plus")
//        }
//    }
//}



//loadQuestionsFromAssets(context: Context, fileName: String): This function takes the Context and the JSON file name as parameters.
//context.assets.open(fileName): Opens the JSON file from the assets folder.
//bufferedReader().use { it.readText() }: Reads the entire file content into a string.
//Gson(): Creates a Gson instance for parsing.
//TypeToken<List<Question>>() {}.type: Creates a TypeToken to tell Gson that you're parsing a list of Question objects.
//gson.fromJson(jsonString, listQuestionType): Parses the JSON string into a List<Question>.
//fileName: This parameter is used to specify the name of the file.
//object com.example.mdd.JsonUtils: This is used to create a singleton.



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MDDTheme {
//        Column {
//            VersionZeroSongtoSongSimilar(
//                "Outta my head",
//                "Pacific Time 2",
//                "Phonte et Carmen Rodgers", "2025",
//                "Motif musical", "Crust",
//                "Yasuke", "Flying Lotus",
//                "2021", "Nakie Nakie",
//                "NEXT", "Electro Deluxe",
//                "2024", "Sonorité, rythme",
//                "Wort it to the top",
//                "Tales From The Land Of Milk And Honey",
//                "The Foreign Exchange","2015",
//                "Hardrock","Sound System",
//                "Herbie Hancock","2024",
//                "Sonorité",
//                "Rockit","Futur Shock",
//                "Herbie Hancock","1983"
//            )
//
//        }
//
//    }
//}
@Preview(showBackground = true)
@Composable
fun GreetingPreviewGreetings() {
    MDDTheme {
        Column {
          MainPage(SampleSimilarMainPage.SimilarMainPageSample)
            //GenreListScreen()
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
