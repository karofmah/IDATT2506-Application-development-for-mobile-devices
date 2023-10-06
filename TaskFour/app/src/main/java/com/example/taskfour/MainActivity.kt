package com.example.taskfour

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOrientation(resources.configuration)
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