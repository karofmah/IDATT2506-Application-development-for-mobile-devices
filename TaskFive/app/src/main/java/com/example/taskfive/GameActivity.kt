package com.example.taskfive

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import okhttp3.*
import okio.ByteString.Companion.encode
import java.io.IOException


class GameActivity : Activity() {

    private val URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"

    private var guessCount = 0

    private val client = OkHttpClient.Builder()
        .cookieJar(object : CookieJar {
            private val myCookieStore = mutableMapOf<HttpUrl, List<Cookie>>()

            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                myCookieStore[url] = cookies
            }

            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                return myCookieStore[url] ?: listOf()
            }
        })
        .build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val registerButton: Button = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            performRegisterRequest()
        }
        val guessNumberButton: Button = findViewById(R.id.guessNumberButton)
        guessNumberButton.setOnClickListener {
            performGuessNumberRequest()
        }
    }
    private fun performRegisterRequest(){
        guessCount = 0

        val name = findViewById<EditText>(R.id.editName).text.toString()
        val cardNumber = findViewById<EditText>(R.id.editCardNumber).text.toString()

        val parameters = FormBody.Builder()
            .add("navn", name)
            .add("kortnummer",cardNumber)
            .build()

        val request = Request.Builder()
            .url(URL)
            .post(parameters)
            .addHeader("Content-Type", "text/plain; charset=utf-8")
            .build()

        Log.d("response", request.toString())

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful){
                    val myResponse = response.body?.string()
                    Log.d("response", response.toString())
                    runOnUiThread {
                        val resultText = findViewById<TextView>(R.id.registerResultText)
                        resultText.text = myResponse
                    }
                }
            }

        })
    }
    private fun performGuessNumberRequest(){
        val number = findViewById<EditText>(R.id.guessNumberText).text.toString()

        if(guessCount < 3) guessCount++
        val parameters = FormBody.Builder()
            .add("tall", number)
            .build()

        val request = Request.Builder()
            .url(URL)
            .post(parameters)
            .addHeader("Content-Type", "text/plain; charset=utf-8")
            .build()
        Log.d("response", request.toString())

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful){
                    val myResponse = response.body?.string().toString()
                    Log.d("response", response.toString())
                    runOnUiThread {
                        val resultText = findViewById<TextView>(R.id.guessResultText)
                        resultText.text = myResponse
                    }
                }
            }

        })
    }

}