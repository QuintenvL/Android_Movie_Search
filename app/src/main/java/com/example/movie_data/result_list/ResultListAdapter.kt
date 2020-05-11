package com.example.movie_data.result_list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movie_data.properties.MovieProperty


class ResultListAdapter(val clickListener: ResultListListener) : ListAdapter<MovieProperty, ResultListViewHolder>(ResultListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultListViewHolder {
        return ResultListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ResultListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindValues(clickListener, item!!)
    }







}