package com.example.taskthree

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText

class AddFriendActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        val backButton = findViewById<Button>(R.id.addBackButton)
        backButton.setOnClickListener{finish()
        }

    }
    fun onClickAddFriend(view: View) {
        val name = findViewById<EditText>(R.id.addName).text.toString()
        val birthDatePicker: DatePicker = findViewById(R.id.datePicker)
        val birthDate = String.format("%02d.%02d.%04d", birthDatePicker.dayOfMonth, birthDatePicker.month + 1, birthDatePicker.year)

        val newFriend = Friend(name, birthDate)
        if(name.isNotEmpty() && birthDate.isNotEmpty()) MainActivity.globalFriendList.add(newFriend)

        setResult(RESULT_OK,Intent())
        finish()
    }
}