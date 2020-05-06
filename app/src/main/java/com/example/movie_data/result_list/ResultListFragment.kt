package com.example.movie_data.result_list

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.movie_data.R
import com.example.movie_data.databinding.ListViewFragmentBinding
import com.example.movie_data.network.MovieProperty

private var movies: List<MovieProperty> = listOf<MovieProperty>(
    MovieProperty(
        "1",
        "Film 1",
        "2002",
        "Me",
        "http://example.com/img1",
        "120 min",
        "Animation, Family",
        "The film ends with a bang"
    ),
    MovieProperty(
        "2",
        "Film 2",
        "2003",
        "Me Too",
        "http://example.com/img2",
        "20 min",
        "Family",
        "The movie ends?!!?"
    ),
    MovieProperty(
        "3",
        "Film 3",
        "2004",
        "Me Too Also",
        "http://example.com/img3",
        "50 min",
        "Comedy, Adventure",
        "They all live happily ever after"
    ),
    MovieProperty(
        "4",
        "Film 4",
        "2005",
        "Me Too What",
        "http://example.com/img4",
        "64 min",
        "Adventure",
        "What happened to the guy in the first scene?"
    ),
    MovieProperty(
        "5",
        "Film 5",
        "2006",
        "Me Too ALtogether",
        "http://example.com/img5",
        "23 min",
        "Drama, Fantasy, Adventure",
        "A man travels around the world in a time machine"
    )
)

class ResultListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ListViewFragmentBinding = DataBindingUtil.inflate<ListViewFragmentBinding>(
            inflater, R.layout.list_view_fragment, container, false
        )

        val resultListViewModel = ResultListViewModel()

        val adapter = ResultListAdapter(ResultListListener { movieId ->
            resultListViewModel.onResultMovieClicked(movieId)
        })

//        resultListViewModel.navigateToMoveDetail.observe(this, Observer {
//            movie -> movie?.let{
//            this.findNavController().navigate(ResultListFragmentDirections)
//        }
//    })
        binding.movieList.adapter = adapter

        adapter.submitList(movies)

        return binding.root
    }
}