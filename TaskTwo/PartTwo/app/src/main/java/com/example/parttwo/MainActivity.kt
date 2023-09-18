package com.example.parttwo

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun  correctAnswer(isAdd: Boolean): Int{
        val numberOneView = findViewById<View>(R.id.numberOneValue) as TextView
        val numberOne: Int = numberOneView.text.toString().toInt()

        val numberTwoView = findViewById<View>(R.id.numberTwoValue) as TextView
        val numberTwo: Int = numberTwoView.text.toString().toInt()

        return if(isAdd)  numberOne + numberTwo else numberOne * numberTwo
    }
    fun onClickAddButton(v: View){

        val answerView = findViewById<EditText>(R.id.editAnswer)
        val answer: Int = answerView.text.toString().toInt()

        if(correctAnswer(true) == answer){
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, getString(R.string.incorrect) + " " + correctAnswer(true), Toast.LENGTH_LONG).show()
        }
    }
    fun onClickMultiplyButton(v: View){

        val answerView = findViewById<EditText>(R.id.editAnswer)
        val answer: Int = answerView.text.toString().toInt()

        if(correctAnswer(false) == answer){
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, getString(R.string.incorrect) + " " + correctAnswer(false), Toast.LENGTH_LONG).show()
        }
    }
}