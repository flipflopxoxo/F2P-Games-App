package com.clydelizardo.f2pgames.repository.api.model

import com.google.gson.annotations.SerializedName

data class GameDetail(
    @SerializedName("description")
    val description: String,
    @SerializedName("developer")
    val developer: String,
    @SerializedName("freetogame_profile_url")
    val freetogameProfileUrl: String,
    @SerializedName("game_url")
    val gameUrl: String,
    @SerializedName("genre")
    val genre: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("minimum_system_requirements")
    val minimumSystemRequirements: MinimumSystemRequirements,
    @SerializedName("platform")
    val platform: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("screenshots")
    val screenshots: List<Screenshot>,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String
)

data class MinimumSystemRequirements(
    @SerializedName("graphics")
    val graphics: String,
    @SerializedName("memory")
    val memory: String,
    @SerializedName("os")
    val os: String,
    @SerializedName("processor")
    val processor: String,
    @SerializedName("storage")
    val storage: String
)

data class Screenshot(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)