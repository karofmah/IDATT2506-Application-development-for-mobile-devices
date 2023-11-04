package com.example.taskseven

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.taskseven.databinding.ActivityMainBinding
import com.example.taskseven.managers.FileManager
import com.example.taskseven.service.Database

class MainActivity : AppCompatActivity() {

    private lateinit var mainLayout: ActivityMainBinding
    private lateinit var db: Database
    private lateinit var fileManager: FileManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainLayout = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainLayout.root)

        fileManager = FileManager(this)
        db = Database(this,fileManager)

        fileManager.write(fileManager.readMoviesFromFile(applicationContext))
    }
    private fun showResults(list: ArrayList<String>) {
        val res = StringBuffer("")
        for (s in list) res.append("$s\n")
        mainLayout.result.text = res
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
       // menuInflater.inflate(R.menu.settings, menu)
        menu.add(0, 1, 0, "All movies")
        menu.add(0, 2, 0, "All movies by Christopher Nolan")
        menu.add(0, 3, 0, "Alle actors in Oppenheimer")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //R.id.settings -> startActivity(Intent("inft2501.leksjon_07.SettingsActivity"))
            1             -> showResults(db.allMovies)
            2             -> showResults(db.getMoviesByDirector("Christopher Nolan"))
            3             -> showResults(db.getActorsForMovie("Oppenheimer"))
            else          -> return false
        }
        return super.onOptionsItemSelected(item)
    }
}