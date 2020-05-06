package com.example.movie_data.result_list

import com.example.movie_data.network.MovieProperty

class ResultListListener(val clickListener: (movieId: String) -> Unit) {
    fun onCLick(movie: MovieProperty) = clickListener(movie.id)
}