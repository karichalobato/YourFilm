package com.xForce.youfilm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.xForce.youfilm.database.daos.MovieDAO
import com.xForce.youfilm.database.daos.MovieInfoDao
import com.xForce.youfilm.database.entities.Movie
import com.xForce.youfilm.database.entities.MovieInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Movie::class, MovieInfo::class], version = 4, exportSchema = false)
public abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDAO
    abstract fun movieInfoDao(): MovieInfoDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope:CoroutineScope
        ): MovieRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room
                        .databaseBuilder(context, MovieRoomDatabase::class.java, "Movie_Database")
                        .fallbackToDestructiveMigration()
//                        .addCallback(RoomDBCallback(scope))
                        .build()
                INSTANCE = instance
                return instance
            }
        }

        private class RoomDBCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDB(database.movieInfoDao(),database.movieDao())
                    }
                }
            }

            suspend fun populateDB(movieInfoDao: MovieInfoDao,movieDAO: MovieDAO) {
                movieInfoDao.deleteAll()
                movieDAO.deleteMovies()
            }
        }
    }

}