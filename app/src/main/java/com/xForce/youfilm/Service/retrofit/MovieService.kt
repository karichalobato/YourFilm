package com.xForce.youfilm.Service.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.xForce.youfilm.database.entities.Movie
import com.xForce.youfilm.database.entities.retrofit.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.omdbapi.com"
interface MovieService {
    @GET("/")
    fun retreiveAllMovies(
            @Query("s")search:String,
            @Query("type")type:String,
            @Query("page")page:Int,
            @Query("apikey")apiKey:String
    ): Deferred<Response<MovieResponse>>

    @GET("/")
    fun retreiveMovieById(
            @Query("i") id: String,
            @Query("apikey") apiKey: String
    ): Deferred<Response<Movie>>

    companion object{
        const val API_KEY = "57eae034"

        fun getMovieService():MovieService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(MovieService::class.java)
    }

}