package com.example.parttwo

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : Activity() {

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
        onClickStartGenerateRandomNumberActivity(1)
        onClickStartGenerateRandomNumberActivity(2)
    }
    fun onClickMultiplyButton(v: View){

        val answerView = findViewById<EditText>(R.id.editAnswer)
        val answer: Int = answerView.text.toString().toInt()

        if(correctAnswer(false) == answer){
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()

        }else{
            Toast.makeText(this, getString(R.string.incorrect) + " " + correctAnswer(false), Toast.LENGTH_LONG).show()
        }
        onClickStartGenerateRandomNumberActivity(1)
        onClickStartGenerateRandomNumberActivity(2)
    }
    private fun onClickStartGenerateRandomNumberActivity(requestCode: Int) {
        val intent = Intent(this, GenerateRandomNumberActivity::class.java)
        val upperLimitView = findViewById<EditText>(R.id.editUpperLimit)
        val upperLimit: Int = upperLimitView.text.toString().toInt()
        intent.putExtra("upperLimit", upperLimit)
        try {
            startActivityForResult(intent,requestCode)
        } catch (e: ActivityNotFoundException) {
            e.message?.let { Log.e("onClickStartGenerateRandomNumberActivity()", it) }
        }
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }
        if (requestCode == 1) {
            val result = data?.getIntExtra("Result", -1)
            val numberOne = findViewById<View>(R.id.numberOneValue) as TextView
            numberOne.text = result.toString()
        }else if(requestCode == 2){
            val result = data?.getIntExtra("Result", -1)
            val numberTwo = findViewById<View>(R.id.numberTwoValue) as TextView
            numberTwo.text = result.toString()
        }else{
            Log.e("onActivityResult()", "Wrong request code")
            return
        }

    }
}