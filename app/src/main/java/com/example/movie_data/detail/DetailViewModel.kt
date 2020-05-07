package com.example.movie_data.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_data.network.MovieProperty

class DetailViewModel(newMovie: MovieProperty?): ViewModel(){

    private val _movie = MutableLiveData<MovieProperty>()
    val movie : LiveData<MovieProperty>
            get() = _movie

    init{
        _movie.value = newMovie
    }


}
