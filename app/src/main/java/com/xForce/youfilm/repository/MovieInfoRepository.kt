package com.xForce.youfilm.repository

import androidx.annotation.WorkerThread
import com.xForce.youfilm.database.daos.MovieInfoDao
import com.xForce.youfilm.database.entities.MovieInfo

class MovieInfoRepository (private val movieInfoDao: MovieInfoDao){

    fun getAllMovieInfo() = movieInfoDao.getAllMovieInfo()
    fun getAllMovieInfoNoLiveData() = movieInfoDao.getAllMovieInfoNoLiveData()
    fun getMoviesByTitle(title:String) = movieInfoDao.findMovieByTitle(title)

    @WorkerThread
    suspend fun insertMovieInfo(movieInfo: MovieInfo) = movieInfoDao.insertOne(movieInfo)

    @WorkerThread
    suspend fun deleteAllMovieInfo() = movieInfoDao.deleteAll()
}