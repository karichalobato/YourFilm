package com.xForce.youfilm.database.entities

import androidx.room.*
import com.squareup.moshi.Json

@Entity(tableName = "movie")


data class Movie(

    @field:Json(name = "imdbID")
    @PrimaryKey
    var imdbID: String,

    @field:Json(name = "Title")
    @ColumnInfo(name = "title")
    val Title: String,

    @field:Json(name = "Year")
    @ColumnInfo(name = "year")
    val Year: String,

    @field:Json(name = "Released")
    @ColumnInfo(name = "released")
    val Released: String,

    @field:Json(name = "Genre")
    @ColumnInfo(name = "genre")
    val Genre: String,

    @field:Json(name = "Director")
    @ColumnInfo(name = "director")
    val Director: String,

    @field:Json(name = "Runtime")
    @ColumnInfo(name = "runtime")
    val Runtime: String,

    @field:Json(name = "Plot")
    @ColumnInfo(name = "plot")
    val Plot: String,

    @field:Json(name = "Poster")
    @ColumnInfo(name = "poster")
    val poster: String,

    @field:Json(name = "Actors")
    @ColumnInfo(name = "actors")
    val actors: String
)
