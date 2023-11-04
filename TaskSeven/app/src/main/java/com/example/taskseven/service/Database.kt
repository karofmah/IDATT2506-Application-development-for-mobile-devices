package com.example.taskseven.service

import android.content.Context
import com.example.taskseven.managers.DatabaseManager
import com.example.taskseven.managers.FileManager
import org.json.JSONObject

class Database(context: Context,fileManager: FileManager) : DatabaseManager(context) {


    init{
        try{

            this.clear()

            val moviesString = fileManager.readMoviesFromFile(context )
            val movies = JSONObject(moviesString).getJSONArray("movies")

            for (i in 0 until movies.length()) {
                val movieJsonObject = movies.getJSONObject(i)
                val title = movieJsonObject.getString("title")
                val director = movieJsonObject.getString("director")
                val actorsJsonArray = movieJsonObject.getJSONArray("actors")

                val actorsList = mutableListOf<String>()
                for (j in 0 until actorsJsonArray.length()) {
                    actorsList.add(actorsJsonArray.getString(j))
                }
                this.insert(title, director, actorsList)

            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
    val allMovies: ArrayList<String>
        get() = performRawQuery(
            select = arrayOf("$MOVIES_TABLE_NAME.$COLUMN_TITLE"),
            from = arrayOf(MOVIES_TABLE_NAME)
        )

    fun getMoviesByDirector(directorName: String): ArrayList<String> {
        return performRawQuery(
            select = arrayOf("$MOVIES_TABLE_NAME.$COLUMN_TITLE"),
            from = arrayOf(MOVIES_TABLE_NAME),
            join = arrayOf(
                "INNER JOIN $DIRECTORS_TABLE_NAME ON $MOVIES_TABLE_NAME.$COLUMN_DIRECTOR_ID = $DIRECTORS_TABLE_NAME.$DIRECTOR_ID"
            ),
            where = "$DIRECTORS_TABLE_NAME.$COLUMN_DIRECTOR_NAME = '$directorName'"
        )
    }

    fun getActorsForMovie(movieTitle: String): ArrayList<String> {
        return performRawQuery(
            select = arrayOf("DISTINCT $ACTORS_TABLE_NAME.$COLUMN_ACTOR_NAME"),
            from = arrayOf(MOVIES_TABLE_NAME),
            join = arrayOf(
                "INNER JOIN $MOVIE_ACTORS_TABLE_NAME ON $MOVIES_TABLE_NAME.$MOVIE_ID = $MOVIE_ACTORS_TABLE_NAME.$MOVIE_ID",
                "INNER JOIN $ACTORS_TABLE_NAME ON $MOVIE_ACTORS_TABLE_NAME.$ACTOR_ID = $ACTORS_TABLE_NAME.$ACTOR_ID"
            ),
            where = "$MOVIES_TABLE_NAME.$COLUMN_TITLE = '$movieTitle'"
        )
    }
}