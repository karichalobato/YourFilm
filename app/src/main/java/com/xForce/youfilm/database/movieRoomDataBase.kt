package com.xForce.youfilm.database

import androidx.room.Database
import com.xForce.youfilm.database.entities.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
public abstract class movieRoomDataBase {
}