package com.example.mdd.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.input.key.type
import com.example.mdd.model.SimilarMusic
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.time.LocalDateTime

object SimilarPageJsonUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseSimilarMusicData(inputStream: InputStream): List<SimilarMusic>? {
        return try {
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val gson = GsonBuilder()
                .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
                .create()
            val listType = object : TypeToken<List<SimilarMusic>>() {}.type
            gson.fromJson(jsonString, listType)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}