package com.xForce.youfilm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xForce.youfilm.R
import com.xForce.youfilm.database.entities.Movie

class MainListFragment : Fragment() {

    private lateinit var  movies :ArrayList<Movie>
    var listenerTool : SearchNewMovieListener? = null

    companion object {
        fun newInstance(dataset : ArrayList<Movie>): MainListFragment{
            val newFragment = MainListFragment()
            newFragment.movies= dataset
            return newFragment
        }
    }

    interface SearchNewMovieListener{
        fun searchMovie(movieName: String)

        fun managePortraitItemClick(movie: Movie)

        fun manageLandscapeItemClick(movie: Movie)
    }

}