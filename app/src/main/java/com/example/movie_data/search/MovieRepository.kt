package com.example.movie_data.search

import com.example.movie_data.network.MovieApi
import com.example.movie_data.network.MovieApiService
//import com.example.movie_data.network.movieApi
import retrofit2.Retrofit

class MovieRepository {

    var client: MovieApiService = MovieApi.retrofitService

    suspend fun getMoviesBySearchTerm(searchTerm: String) = client.getMovieListBySearchTerm(searchTerm)
    suspend fun getDetailsMovie(id: String) = client.getMovieDetails(id)
}