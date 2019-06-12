package com.xForce.youfilm.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.xForce.youfilm.R
import com.xForce.youfilm.activities.ActivityHelper
import com.xForce.youfilm.adapters.MovieAdapter
import com.xForce.youfilm.database.entities.Movie
import com.xForce.youfilm.viewModel.MovieViewModel

class MainListFragment : Fragment() {


    lateinit var movieListRecyclerView: RecyclerView
    lateinit var movieInfoAdapter: MovieAdapter
    lateinit var activityHelper: ActivityHelper
    lateinit var movieInfoViewModel: MovieViewModel
    lateinit var tvSearch: TextView
    lateinit var btnSearch: Button

    val clickListener = fun(view: View, imdbID: String) {
        try {
//            val nextAction = MovieListFragmentDirections.nextAction()
//            nextAction.imdbId = imdbID
//            view.findNavController().navigate(nextAction)
        } catch (e: Exception) {
            Log.e("CUSTOM", e.toString())
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityHelper = context as ActivityHelper
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.movies_list_fragment, container, false)
        movieListRecyclerView = view.findViewById(R.id.movie_list_rv)
        tvSearch = view.findViewById(R.id.movie_name_et)
        btnSearch = view.findViewById(R.id.add_movie_btn)
        movieInfoAdapter = MovieAdapter(clickListener)
        movieListRecyclerView.apply {
            setHasFixedSize(true)
            adapter = movieInfoAdapter
            layoutManager = activityHelper.getLayoutManager()
        }


        movieInfoViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        movieInfoViewModel.getAllMovieInfo().observe(this, Observer {
            if (it.isEmpty()) {
                /*
                  TODO: Verificar si tiene internet
                * */
                movieInfoViewModel.retreiveAllMovieList()
            } else {
                movieInfoAdapter.setData(it)
            }

        })

        btnSearch.setOnClickListener {
            var searchParam = tvSearch.text
            if(searchParam.isEmpty()) activityHelper.showEmptySearchToast()
            else{
                searchParam = addChar(searchParam.toString(),'%',0)
                searchParam = addChar(searchParam.toString(),'%',searchParam.toString().length)
//                Log.d("CUSTOM",searchParam)
                movieInfoViewModel.getMoviesByTittle(searchParam.toString()).observe(this, Observer {
                    movieInfoAdapter.setData(it)
                })
            }
        }

        return view
    }
    fun addChar(str: String, ch: Char, position: Int): String {
        val sb = StringBuilder(str)
        sb.insert(position, ch)
        return sb.toString()
    }

}