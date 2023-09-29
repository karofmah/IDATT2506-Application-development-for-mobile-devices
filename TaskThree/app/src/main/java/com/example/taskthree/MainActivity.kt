package com.example.taskthree

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView

class MainActivity : Activity() {

    private val addFriendRequestCode = 1
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

        onClickEditFriendListener(listView)
    }
    private fun onClickEditFriendListener(listView: ListView){
        listView.onItemClickListener =
            OnItemClickListener { _: AdapterView<*>?, _: View, index: Int, _: Long ->
                val intent = Intent(".EditFriendActivity")
                val name = globalFriendList[index].name
                val birthDate = globalFriendList[index].birthDate
                intent.putExtra("Name",name)
                    .putExtra("Birth Date", birthDate)
                    .putExtra("Index",index)

               startEditFriendActivity()
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
    private fun startEditFriendActivity(){
        try {
            startActivityForResult(intent, editFriendRequestCode)
        } catch (e: ActivityNotFoundException) {
            e.message?.let { Log.e("On start edit friend activity", it) }
        }
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) {
            Log.e("onActivityResult()", "Something went wrong")
            return
        }
        else if (requestCode == addFriendRequestCode || requestCode == editFriendRequestCode) {
            adapter.notifyDataSetChanged()
        } else{
            Log.e("onActivityResult()", "Wrong request code")
            return
        }
    }
}