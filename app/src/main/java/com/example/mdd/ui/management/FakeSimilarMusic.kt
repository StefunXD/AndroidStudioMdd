package com.example.mdd.ui.management

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mdd.ui.elements.CardFakeSimilar
import com.example.mdd.viewmodel.FakeSimilarViewModel

@Composable
fun FakeSimilarScreen(viewModel: FakeSimilarViewModel = viewModel()){
    val similarMusicList by viewModel.similarMusicList.observeAsState(emptyList())
    LazyColumn {
        items(similarMusicList) { similarMusic ->
            CardFakeSimilar(similar = similarMusic)
        }
    }
}

@Composable
fun SimilarMusicScreenPreview(viewModel: FakeSimilarViewModel = viewModel()) {
    val similarMusicList by viewModel.similarMusicList.observeAsState(initial = emptyList())
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {

        viewModel.loadSimilarMusic(context)
    }
    LazyColumn {
        items(similarMusicList) { similarMusic ->
            CardFakeSimilar(similar = similarMusic)
        }
    }
}
