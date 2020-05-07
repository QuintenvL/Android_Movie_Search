package com.example.movie_data.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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

        val viewFactory = SearchViewModelFactory()

        val searchViewModel = ViewModelProvider(this, viewFactory).get(SearchViewModel::class.java)


        binding.Search.setOnQueryTextListener(this.view?.let {
            SearchQueryListener(
                this.context,
                it
            )
        })
        binding.Search.isSubmitButtonEnabled

//        binding.Search.setOnSearchClickListener{ view: View ->
//            view.findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToResultListFragment())
//        }
        return binding.root
    }
}


