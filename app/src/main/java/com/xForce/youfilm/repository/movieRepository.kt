package com.xForce.youfilm.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.xForce.youfilm.database.daos.MovieDAO
import com.xForce.youfilm.database.entities.Movie


class movieRepository (private val movieDao: MovieDAO ) {

    val AllMoview: LiveData<List<Movie>> = movieDao.getAllMovies()

    @WorkerThread
    suspend fun getMovieByTitle(title:String){
        movieDao.getMovieByTitle(title)
    }

    @WorkerThread
    suspend fun deleteMovies(){
        movieDao.deleteMovies()
    }

    @WorkerThread
    suspend fun insertMovie(movie: Movie){
        movieDao.deleteMovies()
    }
}