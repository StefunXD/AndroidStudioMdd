package com.example.mdd.utils

import android.content.Context
import android.util.Log
import com.example.mdd.model.DataBlindTest
import com.google.gson.Gson
import java.io.IOException

object BlindTestQuestionsJsonUtils {

    fun loadBlindTestData(context: Context): DataBlindTest? {
        Log.d("BlindTestJsonUtils", "loadBlindTestData called")
        return try {
            val jsonString = context.assets.open("blindtest.json").bufferedReader().use { it.readText() }
            Log.d("BlindTestJsonUtils", "JSON string: $jsonString")
            val blindTest = Gson().fromJson(jsonString, DataBlindTest::class.java)
            Log.d("BlindTestJsonUtils", "BlindTest loaded: $blindTest")
            blindTest
        } catch (ioException: IOException) {
            Log.e("BlindTestJsonUtils", "Error loading JSON", ioException)
            ioException.printStackTrace()
            null
        }
    }
}