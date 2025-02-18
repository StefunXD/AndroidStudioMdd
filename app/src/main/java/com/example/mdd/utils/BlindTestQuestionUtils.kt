package com.example.mdd.utils

import android.content.Context
import android.util.Log
import androidx.compose.ui.input.key.type
import com.example.mdd.model.DataBlindTest
import com.example.mdd.model.DataBlindTestQuestions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object BlindTestQuestionsJsonUtils {
    fun loadQuestions(context: Context): List<DataBlindTestQuestions> {
        val jsonString: String
        try {
            jsonString = context.assets.open("blindtest.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }
        val listQuestionType = object : TypeToken<List<DataBlindTestQuestions>>() {}.type
        return Gson().fromJson(jsonString, listQuestionType)
    }
}

   /* fun loadBlindTestData(context: Context): List<DataBlindTestQuestions> {
        //Log.d("BlindTestJsonUtils", "loadBlindTestData called")
        return try {
            val jsonString = context.assets.open("blindtest.json").bufferedReader().use { it.readText() }
            //Log.d("BlindTestJsonUtils", "JSON string: $jsonString")
            val blindTest = Gson().fromJson(jsonString, DataBlindTest::class.java)
            //Log.d("BlindTestJsonU", "BlindTest loaded: $blindTest")
            blindTest
        } catch (ioException: IOException) {
            Log.e("BlindTestJsonUtils error loading", "Error loading JSON", ioException)
            ioException.printStackTrace()
            null
        }
    }*/
