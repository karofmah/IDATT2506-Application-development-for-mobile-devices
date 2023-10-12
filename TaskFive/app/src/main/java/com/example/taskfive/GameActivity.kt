package com.example.taskfive


import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class GameActivity : Activity() {


    //private val network: HttpWrapper = HttpWrapper(URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        val button: Button = findViewById(R.id.enterGameButton)

        button.setOnClickListener {
            performRequest()
        }

    }
    private fun performRequest(){
        var url = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"
        val name = findViewById<EditText>(R.id.editName).text.toString()
        val cardNumber = findViewById<EditText>(R.id.editCardNumber).text.toString()

        url+="?navn=${name}&kortnummer=${cardNumber}"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful){
                    val myResponse = response.body?.string()

                    runOnUiThread {
                        val resultText = findViewById<TextView>(R.id.enterResultText)
                        resultText.text = myResponse
                    }
                }
            }

        })
    }

}