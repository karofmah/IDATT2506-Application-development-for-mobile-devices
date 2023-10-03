package com.example.taskfour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.FragmentManager
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
}