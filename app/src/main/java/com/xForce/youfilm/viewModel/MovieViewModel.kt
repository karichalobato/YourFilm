package com.xForce.youfilm.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xForce.youfilm.Service.retrofit.MovieService
import com.xForce.youfilm.database.entities.Movie
import com.xForce.youfilm.database.entities.MovieInfo
import com.xForce.youfilm.database.MovieRoomDatabase
import com.xForce.youfilm.repository.MovieInfoRepository
import com.xForce.youfilm.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(app: Application) : AndroidViewModel(app) {

    private val movieRepository: MovieRepository
    private val movieInfoRepository: MovieInfoRepository

    //private lateinit var scope: CoroutineScope

    init {
        val movieDAO = MovieRoomDatabase.getDatabase(app,viewModelScope).movieDao()
        val movieInfoDao = MovieRoomDatabase.getDatabase(app,viewModelScope).movieInfoDao()


        movieRepository = MovieRepository(movieDAO)
        movieInfoRepository = MovieInfoRepository(movieInfoDao)

    }

    fun retreiveAllMovieList() = viewModelScope.launch(Dispatchers.IO) {
        //        Log.d("CUSTOM","called")
        try {
            val response =
                MovieService.getMovieService().retreiveAllMovies("Spider-Man", "movie", 1, MovieService.API_KEY).await()
            if (response.isSuccessful) {

                with(response) {
                    this.body()?.results?.forEach {
                        insertMovieInfo(it)
                        /*val movieResponse =
                            MovieService.getMovieService().retreiveMovieById(it.imdbID, MovieService.API_KEY).await()
                        if (movieResponse.isSuccessful) {
                            with(movieResponse) {
                                //                                    Log.d("CUSTOM",this.body()!!.Plot)
                                movieRepository.insertMovie(this.body()!!)
                            }
                        }*/
                    }
                }
            } else Log.d("CUSTOM", "shit")
        } catch (e: Exception) {
            Log.e("CUSTOM", e.toString())
        }
//        }
//        else{
//            isFullLiveData.postValue(true)
//        }

    }


    fun retreiveMovie(imdbID:String) = viewModelScope.launch (Dispatchers.IO){

        val response = MovieService.getMovieService().retreiveMovieById(imdbID, MovieService.API_KEY).await()
        if(response.isSuccessful){
            with(response){
                val id = movieRepository.insertMovie(this.body()!!)
                Log.d("CUSTOM",id.toString())
            }
        }

    }


    fun getAllMovieInfo() = movieInfoRepository.getAllMovieInfo()

    fun getMovieById(id: String) = movieRepository.getMovieById(id)


    fun getMoviesByTittle(tittle: String) = movieInfoRepository.getMoviesByTitle(tittle)

    fun insertMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.insertMovie(movie)
    }

    fun insertMovieInfo(movieInfo: MovieInfo) = viewModelScope.launch(Dispatchers.IO) {

        movieInfoRepository.insertMovieInfo(movieInfo)
    }


    fun deleteMovie(imdbID: String) = viewModelScope.launch (Dispatchers.IO){
        movieRepository.deleteMovie(imdbID)
    }

}
