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
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.app.InvestioApp
import com.example.empty_views_activity.navigation.Navigation
import for_sql.ConnectionDB

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Navigation()
        }
//
//        val buttonLoginIn = findViewById<Button>(R.id.buttonLoginIn)
//        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
//        val loginEmailAddress = findViewById<EditText>(R.id.loginEmailAddress)
//
//        val probe = findViewById<TextView>(R.id.probe)
//
//        val connectionDB = ConnectionDB()
//
//        if (connectionDB.isDatabaseConnected()) {
//            probe.text = "Соединение с базой данных установлено"
//        } else {
//            probe.text = "Ошибка при установлении соединения с базой данных"
//        }
//
//        buttonLoginIn.setOnClickListener(){ loginIn(loginEmailAddress) }
//        buttonRegister.setOnClickListener(){ register() }
//
//    }
//
//    fun loginIn(loginEmailAddress: EditText){
//        val intent = Intent(this, ActivityPasswordLogging::class.java)
//
//        intent.putExtra("mail", loginEmailAddress.text.toString())
//        startActivity(intent)
//    }
//
//    fun register(){
//        val intent = Intent(this, RegistrationActivity::class.java)
//        startActivity(intent)
//    }
    }
}


