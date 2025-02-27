package com.example.mdd.utils

import android.content.Context
import com.example.mdd.model.DataFakeSimilar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object FakeSimilarJsonUtils {
    fun parseFakeSimilarMusicJson(context : Context, fileName: String): List<DataFakeSimilar> {
        val jsonString = getJsonDataFromAsset(context, fileName)
        return try {
        val gson = Gson()
        val listType = object : TypeToken<List<DataFakeSimilar>>() {}.type
        gson.fromJson(jsonString, listType) ?: emptyList()
    }
    catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }

    }

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString:String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
    }
}