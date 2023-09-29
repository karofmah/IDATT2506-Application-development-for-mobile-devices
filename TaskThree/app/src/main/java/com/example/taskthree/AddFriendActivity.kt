package com.example.taskthree

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.EditText

class AddFriendActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)
    }

    fun onClickAddFriend(view: View) {
        val name = findViewById<EditText>(R.id.addName).text
        val birthDatePicker: DatePicker = findViewById(R.id.datePicker)
        val birthDate = "${birthDatePicker.dayOfMonth}.${birthDatePicker.month}.${birthDatePicker.year}"
        val result = Intent().putExtra("name", name.toString()).putExtra("Birth Date", birthDate)
        Log.d("Result", name.toString())
        Log.d("Result", result.toString())
        setResult(RESULT_OK,result)

        finish()
    }
}