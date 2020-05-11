package com.example.movie_data.network

class MovieRepository {

    var client: MovieApiService = MovieApi.retrofitService

    suspend fun getMoviesBySearchTerm(searchTerm: String) = client.getMovieListBySearchTerm(searchTerm)
    suspend fun getDetailsMovieById(id: String) = client.getMovieDetails(id)
}