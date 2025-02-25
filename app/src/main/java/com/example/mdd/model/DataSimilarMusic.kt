package com.example.mdd.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class SimilarMusic(
    @SerializedName("id_similar") val idSimilar: Int,
    @SerializedName("song_1") val song1: String,
    @SerializedName("album_1") val album1: String,
    @SerializedName("date_1") val date1: String,
    @SerializedName("artiste_1") val artiste1: String,
    @SerializedName("song_2") val song2: String,
    @SerializedName("album_2") val album2: String,
    @SerializedName("artiste_2") val artiste2: String,
    @SerializedName("date_2") val date2: String,
    @SerializedName("date_creation") val dateCreation: LocalDateTime,
    @SerializedName("id_user") val idUser: List<SimilarDataUserSimilar>,
    @SerializedName("nb_likes") val nbLikes: Int,
    @SerializedName("id_comments") val idComments: List<DataUserComments>,
    @SerializedName("date") val date: String
)

data class SimilarDataUserSimilar(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
    data class SimilarSong(
        @SerializedName("song_title") val songTitle: String,
        val duration: String
    )

    data class SimilarAlbum(
        val id: Int,
        val title: String,
        val releaseDate: String,
        val songs: List<SimilarSong>
    )

    data class SimilarArtist(
        val name: String,
        val albums: List<SimilarAlbum>
    )

    data class MusicData(
        val artists: List<SimilarArtist>
    )

    data class SimilarDataUserComments(
        val id: Int,
        val text: String,
        val date: LocalDateTime,
        val userId: Int
    )
