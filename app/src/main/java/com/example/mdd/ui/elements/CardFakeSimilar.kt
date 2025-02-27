package com.example.mdd.ui.elements

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mdd.R
import com.example.mdd.model.DataFakeSimilar


@Composable
fun CardFakeSimilar(similar: DataFakeSimilar, modifier: Modifier = Modifier){
    Card {
        Row (){
            Image(
                painter = painterResource(id = R.drawable.sharp_android_24),
                contentDescription = "Utilisateur",
                modifier = Modifier
                    .size(30.dp)
            )
            Text(similar.username)
            Spacer(modifier = Modifier.width(8.dp))
            Text(similar.creationDate)

        }
        Text(
            text = "La chanson \"${similar.song1}\" de l'album ${similar.album1} de l'artiste ${similar.artist1} est similaire à \"${similar.song2}\", de l'album ${similar.album2} de l'artiste ${similar.artist2}",
            modifier = modifier
        )
        Text(similar.similar, modifier = Modifier.align(Alignment.CenterHorizontally))
        Row() {
            Image(
                painter = painterResource(id = R.drawable.baseline_thumb_up_alt_24),
                contentDescription = "Like button"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(id = R.drawable.baseline_comment_24),
                contentDescription = "Comment button"
            )
        }
    }
}
