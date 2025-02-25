package com.example.mdd.ui.elements

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mdd.model.SimilarMusic
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DisplaySimilarMusicData(similarMusicList: List<SimilarMusic>) {
    LazyColumn {
        items(similarMusicList) { similarMusic ->
          SimilarMusicItem(similarMusic = similarMusic)
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SimilarMusicItem(similarMusic: SimilarMusic) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "ID Similar: ${similarMusic.idSimilar}")
        Text(text = "Song 1: ${similarMusic.song1} - Album: ${similarMusic.album1} - Artist: ${similarMusic.artiste1}")
        Text(text = "Song 2: ${similarMusic.song2} - Album: ${similarMusic.album2} - Artist: ${similarMusic.artiste2}")
        Text(text = "Date Creation: ${similarMusic.dateCreation?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)}")
        Text(text = "Number of Likes: ${similarMusic.nbLikes}")
        similarMusic.idUser?.forEach { user ->
            Text(text = "User ID: ${user.id} - Name: ${user.name}")
        }
        Text(text = "Comments ID: ${similarMusic.idComments}")
        Text(text = "Date: ${similarMusic.date}")
    }
}



