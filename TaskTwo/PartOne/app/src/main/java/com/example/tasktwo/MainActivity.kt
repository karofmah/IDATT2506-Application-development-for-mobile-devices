package com.example.tasktwo

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private var upperLimit = 100
    private val requestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayToast()
    }
    fun onClickStartGenerateRandomNumberActivity(v: View?) {
        val intent = Intent(this, GenerateRandomNumberActivity::class.java)
        intent.putExtra("upperLimit", upperLimit)
        try {
            startActivityForResult(intent, requestCode)
        } catch (e: ActivityNotFoundException) {
            e.message?.let { Log.e("onClickStartGenerateRandomNumberActivity()", it) }
        }
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }
        if (requestCode == this.requestCode) {
            val result = data?.getIntExtra("Result", -1)
            val resultText = findViewById<View>(R.id.resultTextView) as TextView
            resultText.text = result.toString()
        }

    }
     private fun displayToast(){
        val value = (0..100).random()
        Toast.makeText(this, value.toString(), Toast.LENGTH_LONG).show()
    }
}