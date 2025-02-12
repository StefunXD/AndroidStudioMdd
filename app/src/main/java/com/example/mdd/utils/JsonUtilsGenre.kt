package com.example.mdd.utils

import android.content.Context
import com.example.mdd.model.DataGenre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun loadGenres(context: Context): List<DataGenre> {
    val jsonString: String
    try {
        jsonString = context.assets.open("genremusicdata.json").bufferedReader().use { it.readText() }
    }
    catch (ioException: IOException) {
        ioException.printStackTrace()
        return emptyList()
    }
    val listeGenreType = object: TypeToken<List<DataGenre>>() {}.type
    return Gson().fromJson(jsonString, listeGenreType)
}

