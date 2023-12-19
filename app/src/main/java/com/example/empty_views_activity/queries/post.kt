package com.example.empty_views_activity.queries

import android.util.Log
import com.example.empty_views_activity.modules.Company
import com.example.empty_views_activity.modules.Portfolio
import com.example.empty_views_activity.modules.PortfolioPost
import com.example.empty_views_activity.modules.Stock
import com.example.empty_views_activity.modules.User
import com.example.empty_views_activity.moexAPI.APIClasses.SecuritiesModel
import com.example.empty_views_activity.moexAPI.getSecuritiesModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
suspend fun postStock(name: String, amount: Int, portfolioId: String): Boolean {
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
    val currentDate = Date()
    val calendar = Calendar.getInstance()
    calendar.time = currentDate
    calendar.add(Calendar.DAY_OF_MONTH, -1)
    val newDate = calendar.time
    val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val dateText: String = dateFormat.format(newDate)
    Log.i("Time", dateText)
    val securitiesModel: SecuritiesModel = getSecuritiesModel(tradeData = dateText, name)
    Log.i("APISTOCK", securitiesModel.toString())

    val jsonStock = Json.encodeToString(Stock("", portfolioId, "3", amount, name, securitiesModel.value / securitiesModel.volume, securitiesModel.value / securitiesModel.volume))
    try {
        val response: HttpResponse = client.post("http://10.0.2.2:8080/stock") {
            body = TextContent(jsonStock, ContentType.Application.Json)
        }
        Log.i("PostStock", response.toString())
    }
    catch (e: Exception){
        Log.e("PostStock", e.toString())
        return false
    }
    client.close()
    Log.i("PostStock", "OK")
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

    val json = Json.encodeToString(PortfolioPost("24", user_id, name, 0.0, 0.0, 0.0, 0.0))
    Log.i("json", json)
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