package com.example.mdd.model.DataBlindTestQuestion

import com.google.gson.annotations.SerializedName

data class DataAnwser(
    @SerializedName("text") val text: String,
    @SerializedName("isCorrect") val isCorrect: Boolean

)
