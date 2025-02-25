package com.example.mdd.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class DataPageSimilar(
    @SerializedName("id_similar") val idSimilar: Int? = null,
    @SerializedName("song_1") val song1: String? = null,
    @SerializedName("album_1") val album1: String? = null,
    @SerializedName("date_1") val date1: String? = null, // Changed to String
    @SerializedName("artiste_1") val artiste1: String? = null,
    @SerializedName("song_2") val song2: String? = null,
    @SerializedName("album_2") val album2: String? = null,
    @SerializedName("artiste_2") val artiste2: String? = null,
    @SerializedName("date_2") val date2: String? = null, // Changed to String
    @SerializedName("date_creation") val dateCreation: LocalDateTime? = null,
    @SerializedName("id_user") val idUser: List<DataUser>? = null, // Changed to List<DataUser>
    @SerializedName("nb_likes") val nbLikes: Int? = null,
    @SerializedName("id_comments") val idComments: List<Int>? = null,
    @SerializedName("date") val date: String? = null // Changed to String
)
