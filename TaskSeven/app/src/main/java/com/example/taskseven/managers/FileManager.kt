package com.example.taskseven.managers

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.PrintWriter


class FileManager(private val activity: AppCompatActivity) {
    private val filename: String = "my_movies.json"

    private var dir: File = activity.filesDir
    private var file: File = File(dir, filename)


    fun readMoviesFromFile(applicationContext: Context): String {
        return applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "movies",
                "raw", applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }
    }
    fun write(str: String) {
        PrintWriter(file).use { writer ->
            writer.println(str)
        }
    }
}
