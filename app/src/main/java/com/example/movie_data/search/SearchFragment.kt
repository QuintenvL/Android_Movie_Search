package com.example.movie_data.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
        binding.Search.setOnSearchClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_searchFragment_to_resultListFragment)
        }
        return binding.root
    }
}

