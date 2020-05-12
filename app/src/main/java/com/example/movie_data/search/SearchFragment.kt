package com.example.movie_data.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.movie_data.R
import com.example.movie_data.databinding.SearchViewFragmentBinding
import com.example.movie_data.properties.MovieListProperty

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: SearchViewFragmentBinding = DataBindingUtil.inflate<SearchViewFragmentBinding>(
            inflater,
            R.layout.search_view_fragment,
            container,
            false
        )

        binding.setLifecycleOwner(this)

        val viewFactory = SearchViewModelFactory()

        val searchViewModel = ViewModelProvider(this, viewFactory).get(SearchViewModel::class.java)

        binding.viewModel = searchViewModel

        val editText = binding.searchText

        binding.searchButton.setOnClickListener(
            SearchQueryListener(editText, searchViewModel)
        )

        searchViewModel.detailedMovies.observe(this, Observer {
            if (it != null) {
                val movies = MovieListProperty()
                searchViewModel.stopOngoingJob()
                it?.let {
                        movieList ->
                            movieList.forEach { movie ->
                                movies.add(movie)
                    }
                }
                navigate(SearchFragmentDirections.actionSearchFragmentToResultListFragment(movies))
                searchViewModel.resetFoundMovies()
            }
        })
        return binding.root
    }

    private fun navigate(destination: NavDirections) = with(findNavController()) {
        currentDestination
            ?.getAction(destination.actionId)
            ?.let { navigate(destination) }
    }
}


