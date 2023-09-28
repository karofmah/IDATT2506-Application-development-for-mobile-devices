package com.example.taskthree

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<View>(R.id.friendList) as ListView

        val list = ArrayList<Friend>()
        val one = Friend("one","1")
        list.add(one)

        val adapter = FriendListAdapter(this, 1,list)
        listView.adapter = adapter
    }

    fun onClickStartAddFriendActivity(v: View?) {
        val intent = Intent(".AddFriendActivity")
        try {
            startActivityForResult(intent, 1)
        } catch (e: ActivityNotFoundException) {
            e.message?.let { Log.e("onClickStartAddFriendActivity()", it) }
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