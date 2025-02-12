package com.example.mdd.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import com.example.mdd.model.DataQuotes


fun loadQuotes(context: Context): List<DataQuotes> {
    val jsonString: String
    try {
        jsonString = context.assets.open("logopagequotes.json").bufferedReader().use { it.readText() }
    }
    catch (ioException: IOException) {
        ioException.printStackTrace()
        return emptyList()
    }
    val listeQuoteType = object: TypeToken<List<DataQuotes>>() {}.type
    return Gson().fromJson(jsonString, listeQuoteType)
}

