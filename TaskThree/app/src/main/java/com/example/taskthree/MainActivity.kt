package com.example.taskthree

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.AdapterView.OnItemClickListener

class MainActivity : Activity() {

    private val addFriendRequestCode = 1;
    private val editFriendRequestCode = 2
    companion object{
        val globalFriendList = ArrayList<Friend>()

    }
    private lateinit var adapter: FriendListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = FriendListAdapter(this, 1, globalFriendList)

        val listView = findViewById<View>(R.id.friendList) as ListView
        listView.adapter = adapter

        listView.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, valgt: View, posisjon: Int, id: Long ->
                val intent = Intent(".EditFriendActivity")
                val name = globalFriendList[posisjon].name
                val birthDate = globalFriendList[posisjon].birthDate
                intent.putExtra("Name",name).putExtra("Birth Date", birthDate)
                try {
                    startActivityForResult(intent, editFriendRequestCode)
                } catch (e: ActivityNotFoundException) {
                    e.message?.let { Log.e("On start edit friend activity", it) }
                }
            }
    }

    fun onClickStartAddFriendActivity(v: View?) {
        val intent = Intent(".AddFriendActivity")
        try {
            startActivityForResult(intent, addFriendRequestCode)
        } catch (e: ActivityNotFoundException) {
            e.message?.let { Log.e("onClickStartAddFriendActivity()", it) }
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }
        else if (requestCode == addFriendRequestCode) {

            val name = data?.getStringExtra("name")
            val birthDate = data?.getStringExtra("Birth Date")

            if(name.isNullOrEmpty()){
                Log.e("onActivityResult()", "Name is not defined")
                return
            }
            else if(birthDate.isNullOrEmpty()){
                Log.e("onActivityResult()", "Birth date is not defined")
                return
            }
            else{
                adapter.notifyDataSetChanged()
            }
        }
        else if(requestCode == editFriendRequestCode){

        }else{
            Log.e("onActivityResult()", "Wrong request code")
            return
        }
    }
}