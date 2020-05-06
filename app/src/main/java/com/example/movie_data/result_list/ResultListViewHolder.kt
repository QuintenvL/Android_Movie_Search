package com.example.movie_data.result_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_data.R
import com.example.movie_data.databinding.MovieListItemBinding
import com.example.movie_data.network.MovieProperty

class ResultListViewHolder private constructor(val binding: MovieListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val posterImage: ImageView = binding.listPosterImage

    fun bindValues(clickListener: ResultListListener, item: MovieProperty) {
        binding.listMovieTitle.text = item.title
        binding.listMovieYear.text = item.year
        binding.listMovieDirector.text = item.director
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ResultListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = MovieListItemBinding.inflate(layoutInflater, parent, false)
            return ResultListViewHolder(view)
        }
    }
}