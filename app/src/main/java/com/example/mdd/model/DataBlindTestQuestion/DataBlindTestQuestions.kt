package com.example.mdd.model.DataBlindTestQuestion

import com.google.gson.annotations.SerializedName

data class DataBlindTestQuestions(
  @SerializedName("artiste") val artiste: String,
  @SerializedName("genre") val genre:String,
  @SerializedName("audioFile") val audioFile:String,
  @SerializedName("answers") val answers: List<DataAnwser>
)

