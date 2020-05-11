package com.example.movie_data.network

import com.example.movie_data.properties.MovieProperty
import com.example.movie_data.properties.SearchResultProperty
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "9b0f9c2e"
private const val BASE_URL = "https://www.omdbapi.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService {
    @GET("/")
    suspend fun getMovieListBySearchTerm(
        @Query("s") searchTerm: String,
        @Query("page") page: String = "1",
        @Query("type") type: String = "movie",
        @Query("apikey") key: String = API_KEY
    ): SearchResultProperty

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") movieId: String,
        @Query("apikey") key: String = API_KEY
    ): MovieProperty
}

object MovieApi {
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
