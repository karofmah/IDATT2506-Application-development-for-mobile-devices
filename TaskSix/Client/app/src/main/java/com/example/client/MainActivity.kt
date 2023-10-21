package com.example.client

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView


class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val clientView = findViewById<TextView>(R.id.clientView)
        Client(clientView).start()
    }
}