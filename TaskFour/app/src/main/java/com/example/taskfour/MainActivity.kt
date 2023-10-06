package com.example.taskfour

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object{
        var movieTitleList: Array<String> = arrayOf()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieTitleList = resources.getStringArray(R.array.movieTitleList)
        setOrientation(resources.configuration)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title.toString() == "Previous"){
            if(MovieListFragment.currentMovieIndex > 0){
                changeMovie(MovieListFragment.currentMovieIndex - 1)
            }else{
                changeMovie(2)
            }
        }
        if(item.title.toString() == "Next"){
            if((MovieListFragment.currentMovieIndex) < 2 ) {
                changeMovie(MovieListFragment.currentMovieIndex + 1)
            }else{
                changeMovie(0)
            }
        }
        return true
    }
    private fun changeMovie(index: Int){
        val title: TextView = findViewById(R.id.movieTitle)
        val image: ImageView = findViewById(R.id.movieImage)
        val description: TextView = findViewById(R.id.movieDescription)

        title.text = resources.getStringArray(R.array.movieTitleList)[index]
        image.setImageResource(MovieContentFragment.movieImages[index])
        description.text = resources.getStringArray(R.array.movieDescriptionList)[index]

        MovieListFragment.currentMovieIndex = index
    }
    private fun setOrientation(config: Configuration){
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main)
        }
        if(config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_main_land)
        }
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setOrientation(newConfig)
    }
}