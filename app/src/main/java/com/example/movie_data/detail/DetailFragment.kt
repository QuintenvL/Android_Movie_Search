package com.example.movie_data.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movie_data.R
import com.example.movie_data.databinding.DetailViewFragmentBinding

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DetailViewFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.detail_view_fragment, container, false
        )
        return binding.root
    }

}