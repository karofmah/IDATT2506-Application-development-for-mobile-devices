package com.example.tasktwo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : Activity() {

private var upperBound = 100;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //displayToast()
    }
    fun onClickStartGenerateRandomNumber(v: View?) {
        val intent = Intent(this, GenerateRandomNumberActivity::class.java)
        intent.putExtra("upperBound", upperBound)
        startActivityForResult(intent,1)
    }

    private fun displayToast(){
        val value = (0..100).random()
        Toast.makeText(this, value.toString(), Toast.LENGTH_LONG).show()
    }
}