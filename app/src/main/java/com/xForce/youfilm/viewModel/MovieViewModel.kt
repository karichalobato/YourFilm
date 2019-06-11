package com.xForce.youfilm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.xForce.youfilm.database.entities.Movie
import com.xForce.youfilm.database.movieRoomDataBase
import com.xForce.youfilm.repository.movieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel {
    class MovieViewModel(app: Application) : AndroidViewModel(app) {

        val repository: movieRepository

        //private lateinit var scope: CoroutineScope
        var getAllMovies: LiveData<List<Movie>>

        init {
            val movieDAO = movieRoomDataBase.getDatabase(app).movieDao()

            repository = movieRepository(movieDAO)
            getAllMovies = repository.AllMoview
        }

        suspend fun getMoviesByTittle(tittle: String) = repository.getMovieByTitle(tittle)

        fun insertMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
            repository.insertMovie(movie)
        }

        suspend fun deleteMovies() = repository.deleteMovies()

    }
}