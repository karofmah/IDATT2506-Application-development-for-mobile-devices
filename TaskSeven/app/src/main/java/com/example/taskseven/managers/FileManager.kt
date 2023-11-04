package com.example.taskseven.managers

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.File


class FileManager(private val activity: AppCompatActivity) {
    private val filename: String = ""

    private var dir: File = activity.filesDir
    private var file: File = File(dir, filename)

    fun readMovieFromFile(applicationContext: Context): JSONArray {
        val jsonData = applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "movies",
                "raw",applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }
        Log.i("data", jsonData)
        val jsonObject = JSONObject(jsonData)
        Log.d("data",jsonObject.toString())
        val movies = jsonObject.getJSONArray("movies")
        Log.d("data",movies.toString())
        return movies;
    }

}