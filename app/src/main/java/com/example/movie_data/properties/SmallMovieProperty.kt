package com.example.movie_data.properties

import com.squareup.moshi.Json

data class SmallMovieProperty(
    @Json(name = "imdbID") val id: String,
    @Json(name = "Title") val title: String
)