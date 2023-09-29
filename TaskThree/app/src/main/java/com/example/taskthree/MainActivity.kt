package com.example.taskthree

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : Activity() {

    private val friendList = ArrayList<Friend>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickStartAddFriendActivity(v: View?) {
        val intent = Intent(".AddFriendActivity")
        try {
            startActivityForResult(intent, 1)
        } catch (e: ActivityNotFoundException) {
            e.message?.let { Log.e("onClickStartAddFriendActivity()", it) }
        }
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }
        if (requestCode == 1) {

            val name = data?.getStringExtra("name")
            val birthDate = data?.getStringExtra("Birth Date")

            if(name.isNullOrEmpty()){
                Log.e("onActivityResult()", "Name is not defined")
                return
            }
            if(birthDate.isNullOrEmpty()){
                Log.e("onActivityResult()", "Birth date is not defined")
                return
            }
            val newFriend = Friend(name.toString(),birthDate.toString())
            friendList.add(newFriend)
            Log.d("Result", name.toString())

            val listView = findViewById<View>(R.id.friendList) as ListView

            val adapter = FriendListAdapter(this, 1,friendList)
            listView.adapter = adapter
        }

    }

/*
    private fun createList() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, dyrenavn)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, valgt: View, posisjon: Int, id: Long ->
                findViewById<TextView>(R.id.beskrivelse).text = dyrebeskrivelse[posisjon]
                findViewById<Spinner>(R.id.spinner).setSelection(posisjon)
            }
    }*/
}