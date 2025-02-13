package com.example.mdd.utils

import android.content.Context
import com.example.mdd.model.DataBlindTestQuestion.DataBlindTestQuestions
import com.google.gson.Gson
import java.io.IOException

object BlindTestJsonUtils {

    fun loadBlindTestQuestions(context: Context): DataBlindTestQuestions? {
        return try {
            val jsonString =
                context.assets.open("blindtest.json")
                    .bufferedReader().use { it.readText() }
            Gson().fromJson(jsonString, DataBlindTestQuestions::class.java)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            null
        }
    }
}