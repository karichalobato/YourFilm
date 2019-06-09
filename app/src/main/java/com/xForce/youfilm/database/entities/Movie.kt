package com.xForce.youfilm.database.entities

import androidx.room.*

@Entity(indices = [Index("Title")],
    tableName = "Movie")

data class Movie (

    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "Realeased")
    val realeased: String,
    @ColumnInfo(name = "Runtime")
    val runtime: String,
    @ColumnInfo(name = "Genre")
    val genre: String,
    @ColumnInfo(name = "Director")
    val director: String,
    @ColumnInfo(name = "Actors")
    val actors: String
)

{
    @PrimaryKey(autoGenerate = true)
    var idMovie: Int = 0
}
