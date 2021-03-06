package com.example.movie_data.properties

import com.squareup.moshi.Json

data class SearchResultProperty(
    @Json(name = "Search") val movies: List<SmallMovieProperty>?,
    val totalResults: String?,
    @Json(name = "Response") val response: String
)