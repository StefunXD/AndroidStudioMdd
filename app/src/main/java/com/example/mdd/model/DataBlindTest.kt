package com.example.mdd.model

import com.google.gson.annotations.SerializedName

data class DataBlindTest(
@SerializedName("questions") val questions: List<DataBlindTestQuestions>
)

