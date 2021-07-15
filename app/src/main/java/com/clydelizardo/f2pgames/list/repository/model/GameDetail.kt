package com.clydelizardo.f2pgames.list.repository.model

import com.squareup.moshi.Json

data class GameDetail(
    @Json(name = "short_description")
    val shortDescription: String = "",
    @Json(name = "thumbnail")
    val thumbnail: String = "",
    @Json(name = "game_url")
    val gameUrl: String = "",
    @Json(name = "release_date")
    val releaseDate: String = "",
    @Json(name = "freetogame_profile_url")
    val freetogameProfileUrl: String = "",
    @Json(name = "genre")
    val genre: String = "",
    @Json(name = "publisher")
    val publisher: String = "",
    @Json(name = "developer")
    val developer: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "title")
    val title: String = "",
    @Json(name = "platform")
    val platform: String = ""
)