package com.example.mdd.uimanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mdd.utils.loadGenres


@Composable
fun GenreRaw() {
    val context = LocalContext.current
    val genreList = loadGenres(context)
    Text(
        "Genre", modifier = Modifier
            .padding(bottom = 4.dp),
        )
    LazyRow(modifier = Modifier.padding(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(genreList) { genre ->
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(genre.name)
            }
        }
    }
}
