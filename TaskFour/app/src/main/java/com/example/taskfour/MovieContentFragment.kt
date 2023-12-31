package com.example.taskfour

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


class MovieContentFragment : Fragment() {
    companion object{
        var movieImages = listOf(R.drawable.the_batman,R.drawable.now_you_see_me, R.drawable.blue_beetle)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title: TextView = view.findViewById(R.id.movieTitle)
        val image: ImageView = view.findViewById(R.id.movieImage)
        val description: TextView = view.findViewById(R.id.movieDescription)

        parentFragmentManager.setFragmentResultListener("result", this) { _, result ->
            val index = result.getInt("index")
            title.text = resources.getStringArray(R.array.movieTitleList)[index]
            image.setImageResource(movieImages[index])
            description.text = resources.getStringArray(R.array.movieDescriptionList)[index]
            MovieListFragment.currentMovieIndex = index
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_content, container, false);
    }
}