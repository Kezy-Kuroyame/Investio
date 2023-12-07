package com.example.empty_views_activity.queries

import android.util.Log
import com.example.empty_views_activity.modules.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.InetSocketAddress
import java.net.Proxy

/** Получения данных всех пользователей */
suspend fun getUsers(): MutableList<User> {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        engine {
            // this: AndroidEngineConfig
            connectTimeout = 100_000
            socketTimeout = 100_000
        }

    }
    val response: HttpResponse = client.get("http://10.0.2.2:8080/user")
    Log.i("ResponseStatus", response.call.toString())
    val users: MutableList<User> = response.body()
    client.close()
    return users
}

suspend fun getUserPassword(): MutableList<User> {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        engine {
            // this: AndroidEngineConfig
            connectTimeout = 100_000
            socketTimeout = 100_000
        }

    }
    val response: HttpResponse = client.get("http://10.0.2.2:8080/user")
    Log.i("ResponseStatus", response.call.toString())
    val users: MutableList<User> = response.body()
    client.close()
    return users
}



/** Получение id юзера с помощью email */
suspend fun getIdByEmail(email: String): String? {

    val users: MutableList<User> = getUsers()
    Log.i("Case", users.toString())
    val emailMap = users.associate { it.email to it.id }
    if (emailMap[email] != null){
        return emailMap[email]
    }
    else{
        return null
    }
}

/** Получение всех данных по id юзера
suspend fun getUserById(id: String): String? {

    val users: MutableList<User> = getUsers()
    Log.i("Case", users.toString())
    val emailMap = users.associate { it.id to it.id }
    if (emailMap[email] != null){
        return emailMap[email]
    }
    else{
        return null
    }
}
*/
