package com.xForce.youfilm.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xForce.youfilm.database.entities.Movie

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie):Long

    @Query("SELECT * FROM Movie WHERE title==:title")
    fun getMovieByTitle(title:String): LiveData<List<Movie>>

    @Query("select * from movie")
    fun getAllMovies():LiveData<Movie>

    @Query("select * from movie where imdbID = :id")
    fun getMovieById(id:String):LiveData<Movie>


    @Query("DELETE FROM Movie")
    fun deleteMovies()

    @Query("delete from movie where imdbID = :imdbID")
    suspend fun deleteMovie(imdbID:String)
}