package com.example.taskone

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Karo Mahmoud")
                }

        }
    }

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Cyan) {
        Text(text = "Hi, my name is $name!", modifier = modifier.padding(24.dp))
    }
}

    override fun onCreateOptionsMenu(meny: Menu): Boolean {
        super.onCreateOptionsMenu(meny)
        meny.add("Karo")
        meny.add("Mahmoud")
        Log.i("IDATT2506:", "Created menu")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        if (item.title!!.equals("Karo")) {
            Log.w("IDATT2506:", "Karo is selected")
        } else if(item.title!!.equals("Mahmoud")) {
            Log.e("IDATT2506:", "Mahmoud is selected")
        }
        return true

    }

}













