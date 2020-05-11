package com.example.movie_data.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_data.network.*
import com.example.movie_data.properties.MovieProperty
import kotlinx.coroutines.*

class SearchViewModel : ViewModel() {


    private val _detailedMovies = MutableLiveData<List<MovieProperty>>()
    val detailedMovies: LiveData<List<MovieProperty>>
        get() = _detailedMovies

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _disableSearchButton = MutableLiveData<Boolean>()
    val disableSearchButton: LiveData<Boolean>
        get() = _disableSearchButton

    private val movieRepo = MovieRepository()
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->

        _response.postValue("Server issue. Please try again later")
        _disableSearchButton.postValue(false)
        viewModelJob.cancel()
    }

    init {
        _disableSearchButton.value = false
    }

    fun getMovieResults(searchTerm: String) {

        val collectedMovies = ArrayList<MovieProperty>()
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
                        collectedMovies.add(movie.await())
                    }
                    _response.postValue("")
                    _disableSearchButton.postValue(false)
                    _detailedMovies.postValue(collectedMovies)
                }
            }
        }
    }

    fun resetFoundMovies() {
        _detailedMovies.value = null
    }

    fun stopOngoingJob() {
        viewModelJob.cancel()
        coroutineExceptionHandler.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
