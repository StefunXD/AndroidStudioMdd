package com.example.mdd.model

import com.google.gson.annotations.SerializedName

data class DataUser(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String,
    @SerializedName("profile_photo") val profile_photo: String? = null,
    @SerializedName("id_similar") val id_similar: List<DataSimilar>? = null,
    @SerializedName("id_commentaires") val id_commentaires: List<DataUserComments>? = null,
    @SerializedName("date") val date: String
)
