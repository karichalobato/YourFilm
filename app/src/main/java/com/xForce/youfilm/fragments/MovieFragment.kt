package com.xForce.youfilm.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.xForce.youfilm.R
import com.xForce.youfilm.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.cardview_movie.*
import kotlinx.android.synthetic.main.fragment_movie.*


/*// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

*//**
 * A simple [Fragment] subclass.
 **/



class MovieFragment : Fragment() {

    val args:MovieFragmentArgs by navArgs()

    lateinit var movieTitle:TextView
    lateinit var movieViewModel: MovieViewModel
    lateinit var movieImageView:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_movie, container, false)

        movieViewModel  = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        movieTitle = view.findViewById(R.id.movie_title_main_content_fragment)
        movieImageView = view.findViewById(R.id.image_main_content_fragment)


        movieViewModel.getMovieById(args.imID).observe(this, Observer {
            movieTitle.text = it.Title
            Picasso.get()
                .load(it.poster)
                .into(movieImageView)
            plot_main_content_fragment.text = it.Plot
//            movie_year.text  = it.Year
            released_main_content_fragment.text = it.Year
            runtime_main_content_fragment.text  = it.Runtime
            genre_main_content_fragment.text = it.Genre
            director_main_content_fragment.text = it.Director
            actors_main_content_fragment.text = it.actors
        })

        return view


    }


}
