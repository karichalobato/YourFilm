package com.xForce.youfilm.repository

import androidx.annotation.WorkerThread
import com.xForce.youfilm.database.daos.MovieDAO


class movieRepository (private val movieDao: MovieDAO ) {

    @WorkerThread
    suspend fun delete(){
        return movieDao.deleteMovies()
    }
}