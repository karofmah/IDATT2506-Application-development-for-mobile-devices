package com.example.tasktwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class GenerateRandomNumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_random_number)

        //val upperLimit = intent.getIntExtra("upperLimit", 100)
        //println(upperLimit)

    }
/*
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }

        val upperLimit = data?.getIntExtra("upperLimit",100)
        val value = (0..upperLimit!!).random()

    }*/
}