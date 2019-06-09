package com.xForce.youfilm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xForce.youfilm.database.daos.MovieDAO
import com.xForce.youfilm.database.entities.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
public abstract class movieRoomDataBase : RoomDatabase(){

    abstract fun movieDao(): MovieDAO

    companion object {
        @Volatile
        private  var INSTANCE: movieRoomDataBase? = null

        fun getInstance(
            context: Context
        ): movieRoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room
                    .databaseBuilder(context, movieRoomDataBase::class.java, "Movie_Database")
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }
}