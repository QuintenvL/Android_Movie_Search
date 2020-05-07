package com.example.movie_data.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_data.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    init {
        getMovieRealEstateProperties()
    }

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private fun getMovieRealEstateProperties() {
        MovieApi.retrofitService.getMovieListBySearchTerm("test")
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure: :" + t.message
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                }
            })
    }
}