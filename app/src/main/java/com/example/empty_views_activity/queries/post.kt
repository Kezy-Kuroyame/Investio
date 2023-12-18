package com.example.empty_views_activity.queries

import android.util.Log
import com.example.empty_views_activity.modules.Portfolio
import com.example.empty_views_activity.modules.User
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(InternalAPI::class)
suspend fun postUser(email: String, password: String): Boolean {
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
    val json = Json.encodeToString(User("", email, email, password, ""))
    try {
        val response: HttpResponse = client.post("http://10.0.2.2:8080/user") {
            body = TextContent(json, ContentType.Application.Json)
        }
    }
    catch (e: Exception){
        Log.e("POST", e.toString())
        return false
    }
    client.close()
    Log.i("PostUser", "OK")
    return true
}

@OptIn(InternalAPI::class)
suspend fun postPortfolio(
    user_id: String,
    name: String,
): Boolean
{

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
    val json = Json.encodeToString(Portfolio("", user_id, name, 0.0, 0.0, 0.0, 0.0))
    try {
        val response: HttpResponse = client.post("http://10.0.2.2:8080/portfolio") {
            body = TextContent(json, ContentType.Application.Json)
        }
    }
    catch (e: Exception){
        Log.e("PostPortfolio", e.toString())
        return false
    }
    client.close()
    Log.i("PostPortfolio", "OK")
    return true
}