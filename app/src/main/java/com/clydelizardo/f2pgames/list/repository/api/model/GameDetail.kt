package com.clydelizardo.f2pgames.list.repository.api.model

import com.google.gson.annotations.SerializedName

data class GameDetail(
    @SerializedName("short_description")
    val shortDescription: String = "",
    @SerializedName("thumbnail")
    val thumbnail: String = "",
    @SerializedName("game_url")
    val gameUrl: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("freetogame_profile_url")
    val freetogameProfileUrl: String = "",
    @SerializedName("genre")
    val genre: String = "",
    @SerializedName("publisher")
    val publisher: String = "",
    @SerializedName("developer")
    val developer: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("platform")
    val platform: String = ""
)