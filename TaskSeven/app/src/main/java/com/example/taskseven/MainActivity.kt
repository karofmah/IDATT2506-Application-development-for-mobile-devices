package com.example.taskseven

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.taskseven.databinding.ActivityMainBinding
import com.example.taskseven.managers.DatabaseManager
import com.example.taskseven.managers.FileManager
import com.example.taskseven.service.Database

import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var mainLayout: ActivityMainBinding
    private lateinit var db: Database
    private lateinit var fileManager: FileManager
    private lateinit var databaseManager: DatabaseManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainLayout = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainLayout.root)

        fileManager = FileManager(this)
        db = Database(this)
        databaseManager = DatabaseManager(this)
        addMoviesToDatabase();

        mainLayout.button.setOnClickListener {

        }
    }
    private fun addMoviesToDatabase(){
        val movies = fileManager.convertJsonToList(applicationContext)

        for (i in 0 until movies.length()) {
            val movieJsonObject = movies.getJSONObject(i)
            val title = movieJsonObject.getString("title")
            val director = movieJsonObject.getString("director")
            val actorsJsonArray = movieJsonObject.getJSONArray("actors")

            val actorsList = mutableListOf<String>()
            for (j in 0 until actorsJsonArray.length()) {
                actorsList.add(actorsJsonArray.getString(j))
            }

            databaseManager.clear()
            databaseManager.insert(title, director, actorsList)
        }
    }


}