package com.example.movie_data.result_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultListViewModelFactory(
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultListViewModel::class.java)) {
            return ResultListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}