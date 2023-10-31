
package com.example.server

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class Server(activity: MainActivity, private val textView: TextView, private val PORT: Int = 12345) {
    private val serverText: EditText = activity.findViewById(R.id.message)
    private val sendButton: Button = activity.findViewById(R.id.sendButton)

    private var clientSocket: Socket? = null
    private var clientWriter: PrintWriter? = null

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

    fun start() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                ui = "Starting server"
                val serverSocket = ServerSocket(PORT)
                ui = "ServerSocket is created, waiting for a client to connect..."

                while (true) {
                    val client = withContext(Dispatchers.IO) {
                        serverSocket.accept()
                    }
                    CoroutineScope(Dispatchers.IO).launch {
                        handleClient(client)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                ui = e.message
            }
        }
    }

    private fun handleClient(client: Socket) {
        clientSocket = client
        ui = "A client is conn  ected:\n$clientSocket"

        sendToClient("Welcome client!")

        listenForClientMessages()
    }

    init {
        sendButton.setOnClickListener {
            val messageToSend = serverText.text.toString()
            if (messageToSend.isNotBlank()) {
                CoroutineScope(Dispatchers.IO).launch {
                    sendMessageToClient(messageToSend)
                }
                serverText.text.clear()
            }
        }
    }

    private fun listenForClientMessages() {
        try {
            val reader = BufferedReader(InputStreamReader(clientSocket?.getInputStream()))
            while (true) {
                val message = reader.readLine()
                if (message != null) {
                    ui = "Client says:\n$message"
                } else {

                    reader.close()
                    clientSocket?.close()
                    break
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            ui = "Error reading message from client: ${e.message}"
        }
    }

    private fun sendToClient(message: String) {
        if (clientWriter == null) {
            clientSocket?.let {
                clientWriter = PrintWriter(it.getOutputStream(), true)
            } ?: run {
                ui = "Failed to send message: clientSocket is null"
                return
            }
        }
        clientWriter?.println(message)
        ui = "Sent following to client:\n$message"
    }

    private fun sendMessageToClient(message: String) {
        try {
            clientSocket?.let {
                val writer = PrintWriter(it.getOutputStream(), true)
                writer.println(message)
                ui = "Message sent: $message"
            } ?: run {
                ui = "Failed to send message: clientSocket is null"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ui = "Error sending message: ${e.message}"
        }
    }

}