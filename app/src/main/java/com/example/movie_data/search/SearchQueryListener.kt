package com.example.movie_data.search

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.movie_data.util.clearQuery

class SearchQueryListener(context: Context?, view: View) : SearchView.OnQueryTextListener {
    val parentContext = context
    val searchView = view
    override fun onQueryTextSubmit(query: String?): Boolean {
        var clearedQuery: String = query.clearQuery()
        if (clearedQuery.isEmpty()) {
            return false
        } else {
            Log.i("SearchQueryListener", clearedQuery)
            Toast.makeText(parentContext, clearedQuery, Toast.LENGTH_LONG).show()
//            searchView.findNavController()
//                .navigate(SearchFragmentDirections.actionSearchFragmentToResultListFragment())
            return false
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
