package com.example.mdd.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class DataUserComments(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("text") val text: String,
    @SerializedName("date") val date: LocalDateTime
)
