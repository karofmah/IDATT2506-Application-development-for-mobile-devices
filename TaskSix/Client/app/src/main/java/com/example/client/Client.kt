package com.example.client


import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Client(
    private val textView: TextView,
    activity: MainActivity,
    private val SERVER_IP: String = "10.0.2.2",
    private val SERVER_PORT: Int = 12345,
) {
    private val clientText: EditText = activity.findViewById(R.id.message)
    private val sendButton: Button = activity.findViewById(R.id.sendButton)
    /**
     * Egendefinert set() som gjør at vi enkelt kan endre teksten som vises i skjermen til
     * emulatoren med
     *
     * ```
     * ui = "noe"
     * ```
     */
    private var ui: String? = ""
        set(str) {
            MainScope().launch { textView.text = str }
            field = str
        }

    private lateinit var socket: Socket
    private lateinit var reader: BufferedReader
    private lateinit var writer: PrintWriter

    fun start() {
        CoroutineScope(Dispatchers.IO).launch {
            ui = "Connecting to server..."
            try {
                socket = Socket(SERVER_IP, SERVER_PORT)
                ui = "Connected to server:\n$socket"

                reader = BufferedReader(InputStreamReader(withContext(Dispatchers.IO) {
                    socket.getInputStream()
                }))
                writer = PrintWriter(withContext(Dispatchers.IO) {
                    socket.getOutputStream()
                }, true)

                listenForServerMessages()


            } catch (e: IOException) {
                e.printStackTrace()
                ui = e.message
            }
        }
    }

    private fun listenForServerMessages() {
        while (true) {
            try {
                val message = reader.readLine()
                ui = "Message from server:\n$message"
            } catch (e: IOException) {
                e.printStackTrace()
                ui = "Error reading message from server: ${e.message}"
                break
            }
        }
    }

    init {
        sendButton.setOnClickListener {
            val message = clientText.text.toString()
            if (message.isNotBlank()) {
                CoroutineScope(Dispatchers.IO).launch {
                    sendToServer(message)
                }
                clientText.text.clear()
            }
        }
    }

    private fun sendToServer(message: String) {
        try {
            writer.println(message)
            ui = "Sent following to server: \n\"$message\""
        } catch (e: Exception) {
            e.printStackTrace()
            ui = "Error sending message to server: ${e.message}"
        }
    }
}