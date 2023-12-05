package com.example.empty_views_activity.buttons

import androidx.compose.runtime.MutableState
import com.example.empty_views_activity.queries.getIdByEmail

/** --- Страница входа ---- **/
// Вход
suspend fun LoginInClick(email: MutableState<String>): Int{
    val id = getIdByEmail(email.value)
    if (id == null){
        return -1
    }
    else{
        return id
    }
}