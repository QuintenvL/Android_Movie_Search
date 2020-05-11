package com.example.movie_data.result_list

import com.example.movie_data.properties.MovieProperty

class ResultListListener(val clickListener: (movie: MovieProperty) -> Unit) {
    fun onClick(movie: MovieProperty) = clickListener(movie)
}