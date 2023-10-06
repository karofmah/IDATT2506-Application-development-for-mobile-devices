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
        movieList = resources.getStringArray(R.array.movieList)
        listAdapter = activity?.let {
            ArrayAdapter(it, android.R.layout.simple_list_item_1,
                android.R.id.text1, movieList)
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        val listView = view.findViewById<View>(R.id.movieList) as ListView
        listView.onItemClickListener =
            OnItemClickListener { _: AdapterView<*>?, _: View, index: Int, _: Long ->
                val result = Bundle()
                result.putInt("df1",index)
                parentFragmentManager.setFragmentResult("dataFrom",result)
            }*/

    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        Log.d("click", "click")
        val result = Bundle()
        result.putInt("index",position)
        parentFragmentManager.setFragmentResult("result",result)

    }
}