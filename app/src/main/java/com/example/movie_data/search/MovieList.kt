package com.example.movie_data.search

import android.os.Parcelable
import com.example.movie_data.network.MovieProperty
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieList: ArrayList<MovieProperty>(), Parcelable