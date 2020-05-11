package com.example.movie_data.search

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.movie_data.R
import com.example.movie_data.util.clearQuery
import kotlinx.android.synthetic.main.search_view_fragment.view.*

//class SearchQueryListener(context: Context?, view: View, viewModel: SearchViewModel) : SearchView.OnQueryTextListener {
//    val parentContext = context
//    val searchView = view
//    val searchViewModel = viewModel
//
//    override fun onQueryTextSubmit(query: String?): Boolean {
//        var clearedQuery: String = query.clearQuery()
//        if (clearedQuery.isEmpty()) {
//            return false
//        } else {
//            Log.i("SearchQueryListener", clearedQuery)
//            searchViewModel.getMovieResults(clearedQuery)
//            Toast.makeText(parentContext, clearedQuery, Toast.LENGTH_LONG).show()
////            searchView.findNavController()
////                .navigate(SearchFragmentDirections.actionSearchFragmentToResultListFragment())
//            return false
//        }
//    }
//
//    override fun onQueryTextChange(newText: String?): Boolean {
//
//        Log.i("SearchQueryListener", "Changed the text")
//        Log.i("SearchQueryListener", newText)
//        return false
//    }
//}
class SearchQueryListener(queryField : EditText, viewModel: SearchViewModel) : View.OnClickListener {
    val queryTextField = queryField
    val searchViewModel = viewModel
    override fun onClick(v: View?) {
        val queryText = queryTextField.text.toString()
        val clearedQuery = queryText.clearQuery()
        Log.i("SearchQueryListener", "The query of field")
        Log.i("SearchQueryListener", clearedQuery)
        if (clearedQuery.isNotEmpty()) {
            Log.i("SearchQueryListener", clearedQuery)
            searchViewModel.getMovieResults(clearedQuery)
        }
    }

}