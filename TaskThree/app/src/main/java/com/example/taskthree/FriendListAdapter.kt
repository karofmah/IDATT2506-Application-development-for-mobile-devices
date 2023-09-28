package com.example.taskthree

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class FriendListAdapter(private val context: Activity,resource: Int, private val friends: ArrayList<Friend>): ArrayAdapter<Friend>(context, resource,friends){

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.adapter_view, parent, false)

        val nameText: TextView = rowView.findViewById(R.id.name)
        val birthDateText: TextView = rowView.findViewById(R.id.birthDate)

        nameText.text = friends[position].name
        Log.d("adapter",friends[position].name)
        Log.d("adapter",friends[position].birthDate)

        birthDateText.text = friends[position].birthDate

        return rowView
        /*
        val name: String = getItem(position)?.name ?: ""
        val date: String = getItem(position)?.birthDate ?: ""

        val friend: Friend = Friend(name,date)

        val inflater: LayoutInflater = LayoutInflater.from(context)
        convertView = inflater.inflate(R.layout.adapter_view,parent,false)*/
    }
}

