package com.example.mdd.utils

import android.content.Context
import com.example.mdd.model.DataUser

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object UserJsonUtils {
    fun loadUser(context: Context): List<DataUser> {
        val jsonString: String
        try {
            jsonString = context.assets.open("fakeuserHKayotes.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }
        val listQuestionType = object : TypeToken<List<DataUser>>() {}.type
        return Gson().fromJson(jsonString, listQuestionType)
    }
}