package com.example.movie_data.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "9fd6c664"
private const val BASE_URL = "http://www.omdbapi.com/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService {
    @GET("")
    fun getMovieListBySearchTerm(
        @Query("s") searchTerm: String,
        @Query("type") type: String = "movie",
        @Query("apikey") key: String = API_KEY
    ):
            Call<String>

    @GET("")
    fun getMovieDetails(
        @Query("i") movieId: String,
        @Query("apikey") key: String = API_KEY
    ): Call<String>
}

object MovieApi {
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
