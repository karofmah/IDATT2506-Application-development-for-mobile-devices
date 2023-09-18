package com.example.parttwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickAddButton(v: View){

        val numberOneView = findViewById<View>(R.id.numberOneValue) as TextView
        val numberOne: Int = numberOneView.text.toString().toInt()

        val numberTwoView = findViewById<View>(R.id.numberTwoValue) as TextView
        val numberTwo: Int = numberTwoView.text.toString().toInt()

        val answerView = findViewById<EditText>(R.id.editAnswer);
        val answer: Int = answerView.text.toString().toInt()

        val correctAnswer = numberOne + numberTwo

        if(correctAnswer == answer){
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, getString(R.string.incorrect) + " " + correctAnswer, Toast.LENGTH_LONG).show()
        }
    }
    
}