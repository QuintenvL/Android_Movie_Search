package com.example.movie_data.network

import com.squareup.moshi.Json

data class SmallMovieProperty (
    val Title: String,
    val Year: String,
    @Json(name = "imdbID") val id : String,
    val Type: String,
    @Json(name="Poster") val PosterUrl: String
)