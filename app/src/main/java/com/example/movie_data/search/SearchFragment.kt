package com.example.movie_data.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.movie_data.R
import com.example.movie_data.databinding.SearchViewFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
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

        searchViewModel.ireMovies.observe(this, Observer {
            if (it != null) {
                val movies = MovieList()
                searchViewModel.stopOngoingJob()
                it?.let { movieList ->
                    Log.i("fetchMovie", "in the observer : ")
                    Log.i("fetchMovie", movieList.size.toString())
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

    fun navigate(destination: NavDirections) = with(findNavController()) {
        currentDestination?.getAction(destination.actionId)
            ?.let { navigate(destination) }
    }
}


