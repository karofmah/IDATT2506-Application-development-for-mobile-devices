package com.example.taskfour

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment


class MovieListFragment : ListFragment() {
    companion object{
        var currentMovieIndex = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = activity?.let {
            ArrayAdapter(it, android.R.layout.simple_list_item_1,
                android.R.id.text1, MainActivity.movieTitleList)
        }
    }
    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val result = Bundle()
        result.putInt("index",position)
        parentFragmentManager.setFragmentResult("result",result)
    }
}