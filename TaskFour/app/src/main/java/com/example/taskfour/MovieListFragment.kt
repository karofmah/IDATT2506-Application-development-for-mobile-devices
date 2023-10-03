package com.example.taskfour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class MovieListFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>


    private var movieList: List<String> = listOf("The Batman","Now You See Me","Blue Beetle")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_movie_list, container, false)
        listView = view.findViewById(R.id.movieList)
        adapter =
            activity?.let { ArrayAdapter(it,android.R.layout.simple_list_item_1, movieList) }!!
        listView.adapter = adapter
        listView.onItemClickListener =
            OnItemClickListener { _: AdapterView<*>?, _: View, index: Int, _: Long ->
                val fragmentManager: FragmentManager = parentFragmentManager
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment2, MovieContentFragment::class.java, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit()
                }


        return view
    }


}