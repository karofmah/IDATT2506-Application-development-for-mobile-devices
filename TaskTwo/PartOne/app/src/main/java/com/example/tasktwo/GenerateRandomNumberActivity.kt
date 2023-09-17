package com.example.tasktwo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class GenerateRandomNumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_random_number)
    }

    fun onClickGenerateRandomNumber(view: View) {
        val upperLimit = intent.getIntExtra("upperLimit", 100)
        val result = (0..upperLimit).random()
        setResult(RESULT_OK, Intent().putExtra("Result", result))
        finish()
    }

}