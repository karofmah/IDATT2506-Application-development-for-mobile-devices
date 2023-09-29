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
        val name = findViewById<EditText>(R.id.addName).text.toString()
        val birthDatePicker: DatePicker = findViewById(R.id.datePicker)
        val birthDate = "${birthDatePicker.dayOfMonth}.${birthDatePicker.month}.${birthDatePicker.year}"
        val newFriend = Friend(name, birthDate)

        MainActivity.globalFriendList.add(newFriend)



        val result = Intent().putExtra("name", name).putExtra("Birth Date", birthDate)
        setResult(RESULT_OK,result)
        finish()
    }
}