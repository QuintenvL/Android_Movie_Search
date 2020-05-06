package com.example.movie_data.result_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_data.network.MovieProperty

class ResultListViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<MovieProperty>>()
    val movies: LiveData<List<MovieProperty>>
        get() = _movies

    private val _navigateToMovieDetail = MutableLiveData<String>()
    val navigateToMoveDetail: LiveData<String>
        get() = _navigateToMovieDetail

    fun onResultMovieClicked(id: String){
        _navigateToMovieDetail.value = id
    }

    fun onMovieDetailNavigated() {
        _navigateToMovieDetail.value = null
    }
}