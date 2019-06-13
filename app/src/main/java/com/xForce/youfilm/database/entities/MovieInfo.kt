package com.xForce.youfilm.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_info")
data class MovieInfo(
        @PrimaryKey
        val imdbID: String,
        @ColumnInfo(name = "title")
        val Title: String,
        @ColumnInfo(name = "year")
        val Year: String,
        @ColumnInfo(name = "type")
        val Type: String,
        @ColumnInfo(name = "poster")
        val Poster: String
)