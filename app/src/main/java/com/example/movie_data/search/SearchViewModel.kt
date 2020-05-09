package com.example.movie_data.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_data.network.MovieApi
import com.example.movie_data.network.MovieProperty
import com.example.movie_data.network.SearchResultProperty
import com.example.movie_data.network.SmallMovieProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {


    private val _detailedMovies = MutableLiveData<List<MovieProperty>>()
    val detailedMovies: LiveData<List<MovieProperty>>
        get() = _detailedMovies

    private val _foundMovies = MutableLiveData<List<SmallMovieProperty>>()
    val foundMovies: LiveData<List<SmallMovieProperty>>
        get() = _foundMovies

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getMovieSearchResults()
        fetchMovieDetails(_foundMovies.value)
    }

    private fun getMovieSearchResults() {
        MovieApi.retrofitService.getMovieListBySearchTerm("click")
            .enqueue(object : Callback<SearchResultProperty> {
                override fun onFailure(call: Call<SearchResultProperty>, t: Throwable) {
                    _response.value = "Failure: :" + t.message
                }


                override fun onResponse(
                    call: Call<SearchResultProperty>,
                    response: Response<SearchResultProperty>
                ) {
                    _response.value = response.body()?.movies?.get(0)?.title
                    _foundMovies.value = response.body()?.movies
                }
            })

    }


    private fun fetchMovieDetails(foundMovies: List<SmallMovieProperty>?) {
        var gatheredMovies = ArrayList<MovieProperty>()
        if (foundMovies != null) {
            foundMovies.forEach {
                MovieApi.retrofitService.getMovieDetails(it.id)
                    .enqueue(object : Callback<MovieProperty> {
                        override fun onFailure(call: Call<MovieProperty>, t: Throwable) {
                            _response.value = "Failure: :" + t.message
                        }

                        override fun onResponse(
                            call: Call<MovieProperty>,
                            response: Response<MovieProperty>
                        ) {
                            Log.i("fetchDetails", response.body()?.title)
                            response.body()?.let { movie -> gatheredMovies.add(movie) }
                        }

                    })
            }
            Log.i("gatheredMovies", gatheredMovies.size.toString())
            _detailedMovies.value = gatheredMovies
            _response.value = gatheredMovies.size.toString()
        }
    }
}
