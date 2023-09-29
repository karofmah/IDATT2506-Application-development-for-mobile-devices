package com.example.taskthree

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView

class EditFriendActivity : Activity() {

    private lateinit var birthDatePicker:DatePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_friend)

        val editButton = findViewById<Button>(R.id.editBackButton)
        editButton.setOnClickListener{finish()}

        updateFriend()
    }
    private fun updateFriend(){
        val name = intent.getStringExtra("Name").toString()
        findViewById<TextView>(R.id.editName).text = name
        val birthDate = intent.getStringExtra("Birth Date").toString()
        val day = birthDate.substring(0,2).toInt()
        val month = birthDate.substring(3,5).toInt()
        val year = birthDate.substring(6,10).toInt()

        birthDatePicker= findViewById(R.id.editDatePicker)
        birthDatePicker.updateDate(year,month - 1 ,day)

    }
    fun onClickSaveButton(view: View) {
        val name = findViewById<TextView>(R.id.editName).text.toString()
        val newBirthDate = String.format("%02d.%02d.%04d", birthDatePicker.dayOfMonth, birthDatePicker.month + 1, birthDatePicker.year)
        val index = intent.getIntExtra("Index",-1)
        MainActivity.globalFriendList[index] = Friend(name,newBirthDate)
        setResult(RESULT_OK, Intent())
        finish()
    }
}