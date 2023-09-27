package com.example.taskthree

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView

class MainActivity : Activity() {

    private var dyrenavn: Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

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
    }
}