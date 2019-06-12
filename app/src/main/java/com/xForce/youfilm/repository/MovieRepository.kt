package com.xForce.youfilm.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.xForce.youfilm.database.daos.MovieDAO
import com.xForce.youfilm.database.entities.Movie


class MovieRepository (private val movieDao: MovieDAO ) {

//    val AllMoview: LiveData<List<Movie>> = movieDao.getAllMovies()

    fun getMovieById(id:String) = movieDao.getMovieById(id)


    @WorkerThread
    suspend fun deleteMovies() = movieDao.deleteMovies()

    @WorkerThread
    suspend fun insertMovie(movie: Movie) = movieDao.deleteMovies()
}