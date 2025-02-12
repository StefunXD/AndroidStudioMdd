package com.example.mdd.uimanager

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mdd.R
import com.example.mdd.SimilarMainPage

@Composable
fun similarHomePage(){
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
                Text("User : \"${smp.user_name}\"")
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

}