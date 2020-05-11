package com.example.movie_data.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_data.network.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

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

    private val _disableSearchButton = MutableLiveData<Boolean>()
    val disableSearchButton: LiveData<Boolean>
        get() = _disableSearchButton


    private val _ireMovies = MutableLiveData<List<MovieProperty>>()
    val ireMovies: LiveData<List<MovieProperty>>
        get() = _ireMovies

    private val movieRepo = MovieRepository()
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->

        Log.i("Error", t.toString())
        _response.postValue("Server issue. Please try again later")
        _disableSearchButton.postValue(false)
        viewModelJob.cancel()
    }

    init {
        _disableSearchButton.value = false

//        getMovieResults("click")
//        getMovieSearchResults()
//        fetchMovieDetails(_foundMovies.value)
    }

    fun getMovieResults(searchTerm: String) {

        val ahhMovies = ArrayList<MovieProperty>()
        coroutineScope.launch(coroutineExceptionHandler) {
            _response.postValue("Loading...")
            _disableSearchButton.postValue(true)
            val searchResult = async { movieRepo.getMoviesBySearchTerm(searchTerm) }
            if (!searchResult.await().response.toBoolean()) {
                _response.postValue("No movies found")
                _disableSearchButton.postValue(false)
                Log.i("Exception", "No movies found")
            } else {
                async {
                    searchResult.await().movies?.forEach { smallMovie ->
                        var movie = async { movieRepo.getDetailsMovieById(smallMovie.id) }
                        Log.i("fetchMovie", movie.await().title)
                        ahhMovies.add(movie.await())
                        Log.i("fetchMovie", ahhMovies.size.toString())
//                        if (ahhMovies.size >= searchResult.await().movies?.size!!) {
//                            _ireMovies.postValue(ahhMovies)
//                            _response.postValue(ahhMovies.size.toString())
//                            _disableSearchButton.postValue(false)
//
//                            Log.i("fetchMovie", "successfull added ahhMovies")
//                            Log.i("fetchMovie", _ireMovies.value?.size.toString())
//                        }
                    }

                    Log.i("fetchMovie", "successfull added ahhMovies")
                    Log.i("fetchMovie", _ireMovies.value?.size.toString())
                    _response.postValue("")
                    _disableSearchButton.postValue(false)
                    _ireMovies.postValue(ahhMovies)
                }
            }
        }

    }

    fun resetFoundMovies() {
        _ireMovies.value = null
    }

    fun stopOngoingJob () {
        viewModelJob.cancel()
        coroutineExceptionHandler.cancel()
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
