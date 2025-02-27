package com.example.mdd.model

import com.google.gson.annotations.SerializedName

data class DataFakeSimilar (
    @SerializedName("username")
    val username: String,

    @SerializedName("song1")
    val song1: String,

    @SerializedName("artist1")
    val artist1: String,

    @SerializedName("album1")
    val album1: String,

    @SerializedName("similar")
    val similar: String,

    @SerializedName("song2")
    val song2: String,

    @SerializedName("artist2")
    val artist2: String,

    @SerializedName("album2")
    val album2: String,

    @SerializedName("creationDate")
    val creationDate: String,

    @SerializedName("likes")
    val likes: Int,

    @SerializedName("comments")
    val comments: List<String>
)