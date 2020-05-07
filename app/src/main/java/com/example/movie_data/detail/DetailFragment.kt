package com.example.movie_data.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movie_data.R
import com.example.movie_data.databinding.DetailViewFragmentBinding
import com.example.movie_data.network.MovieProperty

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DetailViewFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.detail_view_fragment, container, false
        )

        val movie = DetailFragmentArgs.fromBundle(arguments!!).movie

        val viewModelFactory = DetailViewModelFactory(movie)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

}