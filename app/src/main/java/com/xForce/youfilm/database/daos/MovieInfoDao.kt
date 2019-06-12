package com.xForce.youfilm.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xForce.youfilm.database.entities.MovieInfo

@Dao

interface MovieInfoDao {
    @Query("select * from movie_info")
    fun getAllMovieInfo():LiveData<List<MovieInfo>>

    @Query("select * from movie_info where title like :title")
    fun findMovieByTitle(title:String): LiveData<List<MovieInfo>>

    @Query("select * from movie_info")
    fun getAllMovieInfoNoLiveData():List<MovieInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(movieInfo: MovieInfo)
    @Query("delete from movie_info")
    suspend fun deleteAll()
}