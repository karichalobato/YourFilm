package com.xForce.youfilm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.xForce.youfilm.R
import com.xForce.youfilm.database.entities.MovieInfo

class MovieAdapter(val clickListener:(View, String)->Unit): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var movies = listOf<MovieInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.cardview_movie,parent,false)
        return ViewHolder(item)
    }

    override fun getItemCount(): Int  = movies.size

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) = holder.bind(movies[position])

    inner class ViewHolder(val item: View):RecyclerView.ViewHolder(item){
        lateinit var itemListMovieName : TextView
        lateinit var itemListPoster: ImageView
        fun bind(movieInfo: MovieInfo) = with(item){

            itemListMovieName = findViewById(R.id.movie_title_cv)
            itemListPoster = findViewById(R.id.movie_image_cv)

            itemListMovieName.text = movieInfo.Title

            Picasso.get()
                    .load(movieInfo.Poster)
                    .into(itemListPoster)

            this.setOnClickListener {
                clickListener(it,movieInfo.imdbID)
            }
        }
    }

    public fun setData(movieInfoList:List<MovieInfo>){
        movies = movieInfoList
        notifyDataSetChanged()
    }
}