package com.example.taskfive

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class GameActivity : AppCompatActivity() {

    private val URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"

    private val network: HttpWrapper = HttpWrapper(URL)

    private var guessesLeft = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val registerButton: Button = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            performRequest(requestRegisterParameters())
        }
        val guessNumberButton: Button = findViewById(R.id.guessNumberButton)
        guessNumberButton.setOnClickListener {
            performRequest(requestGuessNumberParameters())

        }
    }
    @SuppressLint("SuspiciousIndentation")
    private fun requestRegisterParameters(): Map<String, String> {
        guessesLeft = 3
        val name = findViewById<EditText>(R.id.editName).text.toString()
        val cardNumber = findViewById<EditText>(R.id.editCardNumber).text.toString()
        findViewById<TextView>(R.id.countText).text = "0"

        return mapOf(
                    "navn" to name,
                    "kortnummer" to cardNumber,
                )
    }
    private fun requestGuessNumberParameters(): Map<String, String> {
        if(guessesLeft > 0) guessesLeft--
            val number = findViewById<EditText>(R.id.guessNumberText).text.toString()
            return mapOf(
                "tall" to number
            )
    }
    private fun performRequest(parameterList:
    Map<String, String>) {
        CoroutineScope(IO).launch {
            val response: String = try {
                network.get(parameterList)

            } catch (e: Exception) {
                Log.e("performRequest()", e.message!!)
                e.toString()
            }
            MainScope().launch {
                setResult(response)
            }
        }
    }
    private fun setResult(response: String?) {
        findViewById<TextView>(R.id.resultText).text = response
        if(guessesLeft == 0){
            findViewById<TextView>(R.id.countText).text = ""

        }else{
            findViewById<TextView>(R.id.countText).text = (guessesLeft).toString()

        }

    }
}