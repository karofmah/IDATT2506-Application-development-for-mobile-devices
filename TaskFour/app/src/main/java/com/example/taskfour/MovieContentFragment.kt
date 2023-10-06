package com.example.taskfour

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class MovieContentFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val description: TextView = view.findViewById(R.id.movieDescription)
        description.text = resources.getStringArray(R.array.movieList)[0]

        parentFragmentManager.setFragmentResultListener("result", this) { requestKey, result ->
            val index = result.getInt("index")
            description.text = resources.getStringArray(R.array.movieList)[index]
            Log.d("index", index.toString())

        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_content, container, false);
    }


}