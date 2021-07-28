package com.clydelizardo.f2pgames.list.viewmodel.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class GameInfo(
    val id: String,
    val name: String,
    val description: String,
    val releaseDate: Date?,
    val genre: String,
    val platform: String,
    val link: String,
    val isFavorite: Boolean,
    val thumbnail: String,
    val publisher: String,
    val developer: String
) : Parcelable
