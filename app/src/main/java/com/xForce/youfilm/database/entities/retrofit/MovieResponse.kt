package com.xForce.youfilm.database.entities.retrofit

import com.squareup.moshi.Json
import com.xForce.youfilm.database.entities.MovieInfo

class MovieResponse (
        @field:Json(name = "Search")
        val results:List<MovieInfo>
)