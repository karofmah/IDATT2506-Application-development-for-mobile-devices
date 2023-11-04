package com.example.taskseven.managers

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter


class FileManager(private val activity: AppCompatActivity) {

    fun readMoviesFromFile(name : String): String {
        val content = StringBuilder()

        val resourceId = activity.resources.getIdentifier(name, "raw", activity.packageName)

        try {
            val inputStream: InputStream =activity.resources.openRawResource(resourceId)
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                var line = reader.readLine()
                while (line != null) {
                    content.append(line)
                    content.append("\n")
                    line = reader.readLine()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Log.d("FileManager", "Movies file content:\n$content")
        return content.toString()
    }
    fun readFileFromInternalStorage(filename: String): String {
        val content = StringBuilder()
        try {
            activity.openFileInput(filename).use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    var line = reader.readLine()
                    while (line != null) {
                        content.append(line)
                        content.append("\n")
                        line = reader.readLine()
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Log.d("FileManager", "Internal storage file content:\n$content")
        return content.toString()
    }
    fun writeToFile(filename: String, content: String) {
        try {

            val outputStream = activity.openFileOutput(filename, Context.MODE_PRIVATE)
            OutputStreamWriter(outputStream).use { writer ->
                writer.write(content)
            }
            Log.d("FileManager", "Data written to file: $filename")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}