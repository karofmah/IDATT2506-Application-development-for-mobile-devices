package com.example.taskseven

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import com.example.taskseven.databinding.ActivityChangeColorBinding

class ChangeColorActivity : Activity() {

    companion object{
        var globalBackgroundColor = R.color.white
    }
    private lateinit var changeColorLayout: ActivityChangeColorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeColorLayout = ActivityChangeColorBinding.inflate(layoutInflater)
        setContentView(changeColorLayout.root)


        findViewById<Button>(R.id.whiteButton).setOnClickListener {
            globalBackgroundColor = R.color.white
            finish()
        }

        findViewById<Button>(R.id.yellowButton).setOnClickListener {
            globalBackgroundColor = R.color.yellow
            finish()
        }

        findViewById<Button>(R.id.tealButton).setOnClickListener {
            globalBackgroundColor = R.color.teal_700
            finish()
        }
    }
}