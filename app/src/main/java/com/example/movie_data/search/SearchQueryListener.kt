package com.example.movie_data.search

import android.view.View
import android.widget.EditText

class SearchQueryListener(queryField : EditText, viewModel: SearchViewModel) : View.OnClickListener {
    private val queryTextField = queryField
    private val searchViewModel = viewModel

    override fun onClick(v: View?) {
        val queryText = queryTextField.text.toString()
        val clearedQuery = queryText.clearQuery()
        if (clearedQuery.isNotEmpty()) {
            searchViewModel.getMovieResults(clearedQuery)
        }
    }

    private fun String?.clearQuery(): String {
        if (this == null) {
            return ""
        }
        return this.trim()
    }

}