package com.example.mdd.model

import com.google.gson.annotations.SerializedName

data class DataSimilar(
    @SerializedName("id") val id: Int,
    @SerializedName("similar") val similar: String,
    @SerializedName("description") val description: String
)
