package com.example.taskthree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView

class EditFriendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_friend)

        updateFriend();
    }
    private fun updateFriend(){

        findViewById<TextView>(R.id.editName).text = intent.getStringExtra("Name")
        val birthDate = intent.getStringExtra("Birth Date")
        val day = birthDate?.substring(0,2)?.toInt()
        val month = birthDate?.substring(3,5)?.toInt()
        val year = birthDate?.substring(6,10)?.toInt()
        if (year != null && month != null && day != null) {
            findViewById<DatePicker>(R.id.datePicker).updateDate(year,month,day)
        }

    }
    fun onClickSaveButton(view: View) {
        finish()
    }
}