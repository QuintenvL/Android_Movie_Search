package com.example.movie_data.result_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie_data.properties.MovieProperty

class ResultListViewModelFactory( private val movies: List<MovieProperty>
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultListViewModel::class.java)) {
            return ResultListViewModel(movies) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}