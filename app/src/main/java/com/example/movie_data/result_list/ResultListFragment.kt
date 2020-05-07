package com.example.movie_data.result_list

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movie_data.R
import com.example.movie_data.databinding.ListViewFragmentBinding
import com.example.movie_data.detail.DetailViewModel
import com.example.movie_data.detail.DetailViewModelFactory
import com.example.movie_data.network.MovieProperty


class ResultListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ListViewFragmentBinding = DataBindingUtil.inflate<ListViewFragmentBinding>(
            inflater, R.layout.list_view_fragment, container, false
        )
        binding.setLifecycleOwner(this)

        val viewModelFactory = ResultListViewModelFactory()

        val resultListViewModel = ViewModelProvider(this, viewModelFactory).get(ResultListViewModel::class.java)

        val adapter = ResultListAdapter(ResultListListener { movie ->
            resultListViewModel.onResultMovieClicked(movie)
        })

        resultListViewModel.navigateToMoveDetail.observe(this, Observer {
            movie -> movie?.let{
            this.findNavController().navigate(ResultListFragmentDirections.actionResultListFragmentToDetailFragment(it))
            resultListViewModel.onMovieDetailNavigated()
        }
    })
        binding.movieList.adapter = adapter

        adapter.submitList(resultListViewModel.movies.value)

        return binding.root
    }
}