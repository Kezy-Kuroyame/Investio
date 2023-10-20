package com.example.empty_views_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonLoginIn = findViewById<Button>(R.id.buttonLoginIn)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        val loginEmailAddress = findViewById<EditText>(R.id.loginEmailAddress)

        buttonLoginIn.setOnClickListener(){ loginIn(loginEmailAddress) }
        buttonRegister.setOnClickListener(){ register() }
    }

    fun loginIn(loginEmailAddress: EditText){
        val intent = Intent(this, ActivityPasswordLogging::class.java)

        intent.putExtra("mail", loginEmailAddress.text.toString())
        startActivity(intent)
    }

    fun register(){
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }
}