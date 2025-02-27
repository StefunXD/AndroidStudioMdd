package com.example.mdd.utils

import android.content.Context
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


