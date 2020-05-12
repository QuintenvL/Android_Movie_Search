package com.example.movie_data.result_list

import androidx.recyclerview.widget.DiffUtil
import com.example.movie_data.properties.MovieProperty

class ResultListDiffCallback : DiffUtil.ItemCallback<MovieProperty>() {

    override fun areItemsTheSame(oldItem: MovieProperty, newItem: MovieProperty): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieProperty, newItem: MovieProperty): Boolean {
        return oldItem == newItem
    }
}