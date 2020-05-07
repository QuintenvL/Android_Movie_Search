package com.example.movie_data.result_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_data.network.MovieProperty

private var movieList: List<MovieProperty> = listOf<MovieProperty>(
    MovieProperty(
        "1",
        "Film 1",
        "2002",
        "Me",
        "http://example.com/img1",
        "120 min",
        "Animation, Family",
        "The film ends with a bang"
    ),
    MovieProperty(
        "2",
        "Film 2",
        "2003",
        "Me Too",
        "http://example.com/img2",
        "20 min",
        "Family",
        "The movie ends?!!?"
    ),
    MovieProperty(
        "3",
        "Film 3",
        "2004",
        "Me Too Also",
        "http://example.com/img3",
        "50 min",
        "Comedy, Adventure",
        "They all live happily ever after"
    ),
    MovieProperty(
        "4",
        "Film 4",
        "2005",
        "Me Too What",
        "http://example.com/img4",
        "64 min",
        "Adventure",
        "What happened to the guy in the first scene?"
    ),
    MovieProperty(
        "5",
        "Film 5",
        "2006",
        "Me Too ALtogether",
        "http://example.com/img5",
        "23 min",
        "Drama, Fantasy, Adventure",
        "A man travels around the world in a time machine"
    )
)

class ResultListViewModel : ViewModel() {

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
        _movies.value = movieList
    }
}