package com.example.movie_data.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieProperty(
    val id: String,
    val title: String,
    val year: String,
    val director: String,
    val posterUrl: String,
    val runTime: String,
    val genre: String,
    val plot: String
) : Parcelable