package com.example.movie_data.result_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_data.properties.MovieProperty

class ResultListViewModel(searchMovies: List<MovieProperty>) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieProperty>>()
    val movies: LiveData<List<MovieProperty>>
        get() = _movies

    private val _navigateToMovieDetail = MutableLiveData<MovieProperty>()
    val navigateToMoveDetail: LiveData<MovieProperty>
        get() = _navigateToMovieDetail

    fun onResultMovieClicked(movie: MovieProperty){
        _navigateToMovieDetail.value = movie
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }

    init {
        _movies.value = searchMovies
    }
}