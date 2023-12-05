package com.example.empty_views_activity.queries

import android.util.Log
import com.example.empty_views_activity.modules.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/** Получения данных всех пользователей */
suspend fun getUsers(): MutableList<User> {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }
    val response: MutableList<User> = client.get("http://127.0.0.1:8080/user").body()
    Log.i("Case", response.toString())
    client.close()
    return response
}

/** Получение id юзера с помощью email */
suspend fun getIdByEmail(email: String): Int? {

    val users: MutableList<User> = getUsers()
//    val emailMap = users.associate { it.email to it.id }
//    if (emailMap[email] != null){
//        return emailMap[email]!!.toInt()
//    }
//    else{
//        return null;
//    }
    return 1
}