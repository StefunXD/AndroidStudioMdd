package com.example.mdd.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mdd.model.SimilarDataUserSimilar
import com.example.mdd.model.SimilarMusic


object SampleSimilarMusicData {
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadSampleSimilarMusicData(context: Context): List<SimilarMusic> {
        return try {
            val inputStream = context.assets.open("similar_music.json") // Replace with your file name
            val similarMusicList = SimilarPageJsonUtils.parseSimilarMusicData(inputStream)
            if (similarMusicList != null) {
                similarMusicList.map { similarMusic ->
                    similarMusic.copy(
                        idUser = similarMusic.idUser.orEmpty().map { dataUser ->
                            SimilarDataUserSimilar(id = dataUser.id ?: 0, name = dataUser.name ?: "")
                        }
                    )
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}