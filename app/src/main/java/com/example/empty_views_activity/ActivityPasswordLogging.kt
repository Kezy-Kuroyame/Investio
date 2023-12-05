package com.example.empty_views_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class ActivityPasswordLogging : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_logging)

        val buttonContinueLoginIn = findViewById<Button>(R.id.buttonContinueLoginIn)
        val buttonBack = findViewById<ImageButton>(R.id.buttonLoginBack)
        val exampleMain = findViewById<TextView>(R.id.exampleEmail)
        exampleMain.text = intent.getStringExtra("mail")

        buttonBack.setOnClickListener {
            backToLoginIn()
        }
    }
    fun backToLoginIn(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}