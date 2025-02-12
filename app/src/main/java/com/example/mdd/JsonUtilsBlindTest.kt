package com.example.mdd

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object JsonUtils{
    fun loadQuestionForBlindTest(context: Context, fileName: String): List<Question> {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }
        val gson = Gson()
        val listQuestionType = object :
            TypeToken<List<Question>>() {}.type
        return gson.fromJson(jsonString, listQuestionType)

    }
}
