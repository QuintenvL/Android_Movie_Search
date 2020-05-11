package com.example.movie_data.result_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movie_data.R
import com.example.movie_data.databinding.ListViewFragmentBinding


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

        val movies = ResultListFragmentArgs.fromBundle(arguments!!).searchResult

        val viewModelFactory = ResultListViewModelFactory(movies)

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