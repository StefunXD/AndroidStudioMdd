package com.example.mdd.model

import com.google.gson.annotations.SerializedName

data class DataBlindTestAnwser(
    @SerializedName("text") val text: String,
    @SerializedName("isCorrect") val isCorrect: Boolean

)
