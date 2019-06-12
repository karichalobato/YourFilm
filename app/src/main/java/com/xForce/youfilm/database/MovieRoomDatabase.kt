package com.xForce.youfilm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xForce.youfilm.database.daos.MovieDAO
import com.xForce.youfilm.database.daos.MovieInfoDao
import com.xForce.youfilm.database.entities.Movie
import com.xForce.youfilm.database.entities.MovieInfo

@Database(entities = [Movie::class,MovieInfo::class], version = 2, exportSchema = false)
public abstract class MovieRoomDatabase : RoomDatabase(){

    abstract fun movieDao(): MovieDAO
    abstract fun movieInfoDao(): MovieInfoDao

    companion object {
        @Volatile
        private  var INSTANCE: MovieRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): MovieRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room
                    .databaseBuilder(context, MovieRoomDatabase::class.java, "Movie_Database")
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }

}