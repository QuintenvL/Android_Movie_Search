package com.example.movie_data.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movie_data.network.MovieApi
import com.example.movie_data.network.MovieProperty
import com.example.movie_data.network.SearchResultProperty
import com.example.movie_data.network.SmallMovieProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val movieRepository = MovieRepository()
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val _searchedMovies = MutableLiveData<SearchResultProperty>()

    private val _detailedMovies = MutableLiveData<List<MovieProperty>>()
    val detailedMovies: LiveData<List<MovieProperty>>
        get() = _detailedMovies

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _navigateToListView = MutableLiveData<Boolean>()
    val navigateToListView : LiveData<Boolean>
            get() = _navigateToListView

    init {
        getMovieRealEstateProperties()
        _detailedMovies.value = ArrayList<MovieProperty>()
        _navigateToListView.value = false
    }

    private fun getMovieRealEstateProperties() {

//        val post = liveData(Dispatchers.IO) {
//            val mo = movieRepository.getMoviesBySearchTerm("test")
//            emit(mo)
//        }
//        fetchMovieDetails(post.value?.movies!!)
//        _response.value = _detailedMovies.value?.size.toString()

//        coroutineScope.launch {
//            val foundMovies = withContext(Dispatchers.IO) {
//                movieRepository.getMoviesBySearchTerm("test")
//            }
//            fetchMovieDetails(foundMovies.movies)
//            _response.value = _detailedMovies.value?.size.toString()
//        }



//        coroutineScope.launch {
//                var getMovieListBySearchTerm = MovieApi.retrofitService.getMovieListBySearchTerm("test")
//                try {
//                    var response = getMovieListBySearchTerm.await()
//                    fetchMovieDetails(response.movies)
//
//                } catch (e: Exception) {
//                    _response.value = e.message
//                }
//        }

        MovieApi.retrofitService.getMovieListBySearchTerm("test")
            .enqueue(object : Callback<SearchResultProperty> {
                override fun onFailure(call: Call<SearchResultProperty>, t: Throwable) {
                    _response.value = t.message
//                    _response.value = "Internet issue. PLease try again later."
                }

                override fun onResponse(
                    call: Call<SearchResultProperty>,
                    response: Response<SearchResultProperty>
                ) {

                    fetchMovieDetails(response.body()?.movies!!)
//                    _response.value = _detailedMovies.value?.size.toString()
                    _response.value = response.body()?.totalResults
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun fetchMovieDetails(movies: List<SmallMovieProperty>) {
        var gatheredMovies = ArrayList<MovieProperty>()
//        coroutineScope.launch {
//            movies.forEach {
//                var movieDetails = withContext(Dispatchers.IO) {
//                    movieRepository.getDetailsMovie(it.id)
//                }
//                gatheredMovies.add(movieDetails)
//            }
//        }
//        movies.forEach {
//            val movieDetails = liveData(Dispatchers.IO) {
//                var details = movieRepository.getDetailsMovie(it.id)
//                emit(details)
//            }
//            gatheredMovies.add(movieDetails.value!!)
//        }
        _detailedMovies.value = gatheredMovies
    }

//        movies.forEach {
//            MovieApi.retrofitService.getMovieDetails(it.id)
//                .enqueue(object: Callback<MovieProperty> {
//                    override fun onFailure(call: Call<MovieProperty>, t: Throwable) {
//                        _response.value = "Internet issue. PLease try again later."
//                    }
//
//                    override fun onResponse(
//                        call: Call<MovieProperty>,
//                        response: Response<MovieProperty>
//                    ) {
//                        response.body()?.let { it1 -> gatheredMovies.add(it1) }
//                    }
//
//                })
//        }

    fun doneNavigatingToListView () {
        _navigateToListView.value = false
    }
}