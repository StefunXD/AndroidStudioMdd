package com.example.mdd.utils

import android.content.Context
import android.os.Build
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.compose.ui.input.key.type
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.time.LocalDateTime

object JsonUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun <T> loadJsonData(context: Context, fileName: String, typeToken: TypeToken<List<T>>): List<T> {
        val jsonString = getJsonFromAssets(context, fileName)
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            .create()
        return gson.fromJson(jsonString, typeToken.type)
    }

    @OptIn(UnstableApi::class)
    private fun getJsonFromAssets(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer)
        } catch (e: IOException) {
            Log.e("JsonUtils", "Error reading JSON file", e)
            return null
        }
        return jsonString
    }
}