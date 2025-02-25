package com.example.mdd.utils

import android.content.Context
import android.os.Build
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.mdd.model.DataUserComments
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.time.LocalDateTime

object UserCommentsJsonUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadUserComments(context: Context): List<DataUserComments> {
        val jsonString = getJsonFromAssets(context, "fakesimilarcomments.json")
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            .create()
        val listUserCommentType = object : TypeToken<List<DataUserComments>>() {}.type
        return gson.fromJson(jsonString, listUserCommentType)
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
            Log.e("UserCommentsJsonUtils", "Error reading JSON file", e)
            return null
        }
        return jsonString
    }
}