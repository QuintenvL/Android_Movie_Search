package com.example.movie_data.properties

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieProperty(
    @Json(name = "imdbID") val id: String,
    @Json(name = "Title") val title: String,
    @Json(name = "Year") val year: String,
    @Json(name = "Director") val director: String,
    @Json(name = "Poster") val posterUrl: String,
    @Json(name = "Runtime") val runTime: String,
    @Json(name = "Genre") val genre: String,
    @Json(name = "Plot") val plot: String
) : Parcelable