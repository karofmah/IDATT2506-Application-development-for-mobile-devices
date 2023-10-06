package com.example.taskfour

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment


class MovieListFragment : ListFragment() {

    private var movieList: Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieList = resources.getStringArray(R.array.movieTitleList)
        listAdapter = activity?.let {
            ArrayAdapter(it, android.R.layout.simple_list_item_1,
                android.R.id.text1, movieList)
        }

    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        Log.d("click", "click")
        val result = Bundle()
        result.putInt("index",position)
        parentFragmentManager.setFragmentResult("result",result)

    }
}