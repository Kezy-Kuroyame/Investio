package com.example.empty_views_activity.buttons

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.empty_views_activity.modules.Stock
import com.example.empty_views_activity.queries.getIdByEmail
import com.example.empty_views_activity.queries.getStockByPortfolioId
import com.example.empty_views_activity.queries.postUser
import io.ktor.client.call.body
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.json.Json

/** --- Страница входа ---- **/
// Вход
suspend fun LoginInClick(email: MutableState<String>): String{
    val id = getIdByEmail(email.value.lowercase())
    if (id == null){
        return "-1"
    }
    else{
        return id
    }
}

/** --- Страница Регистрации ---- **/
suspend fun signUpClick(args: MutableList<MutableState<String>>, err: MutableList<MutableState<Boolean>>): Boolean{
    val email = args[0]
    val password1 = args[1]
    val password2 = args[2]

    val isErrorEmail = err[0]
    val isErrorPasswords = err[1]
    val isPasswordEmpty = err[2]
    val isEmailEmpty = err[3]


    if (email.value.isNotEmpty()) {
        Log.i("SignUp/EmailNotEmpty", "OK")
        val id = LoginInClick(email)
        if (id == "-1") {
            Log.i("SignUp/Email", "OK")
            if (password1.value == password2.value) {
                Log.i("SignUp/PassEqual", "OK")
                if (password1.value.isNotEmpty()) {
                    Log.i("SignUp/PassNotEmpty", "OK")
                    if (postUser(email.value, password1.value)) {
                        return true
                    }
                } else {
                    Log.e("SignUp/PassNotEmpty", "Пустое поле")
                    isPasswordEmpty.value = true
                }
            } else {
                Log.e("SignUp/PassEqual", "Пароли не равны")
                isErrorPasswords.value = true
            }
        } else {
            Log.e("SignUp/Email", "Такой Email уже существует")
            isErrorEmail.value = true
        }
    } else {
        Log.e("SignUp/EmailNotEmpty", "Пустое Email поле")
        isEmailEmpty.value = true
    }
    return false
}


