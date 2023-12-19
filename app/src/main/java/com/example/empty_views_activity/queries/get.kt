package com.example.empty_views_activity.queries

import android.util.Log
import com.example.empty_views_activity.modules.Portfolio
import com.example.empty_views_activity.modules.Stock
import com.example.empty_views_activity.modules.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

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

suspend fun getPortfolios(): MutableList<Portfolio>{
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
    val response: HttpResponse = client.get("http://10.0.2.2:8080/portfolio")
    Log.i("ResponseStatus", response.call.toString())
    val portfolios: MutableList<Portfolio> = response.body()
    client.close()
    return portfolios
}
suspend fun getPortfolioById(id: String): Portfolio{
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
    val response: HttpResponse = client.get("http://10.0.2.2:8080/portfolio/${id}")
    Log.i("GetPortfolioByID", response.call.toString())
    val portfolio: Portfolio = response.body()
    client.close()
    return portfolio
}

suspend fun getStockByPortfolioId(portfolioId: String): MutableList<Stock>{
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
    val response: HttpResponse = client.get("http://10.0.2.2:8080/stock/portfolio/${portfolioId}")
    Log.i("ResponseStatus", response.call.toString())
    val stock: MutableList<Stock> = response.body()
    client.close()
    return stock
}

suspend fun countUsersPortfolio(userId: String): Int{
    val portfolios = getPortfolios()
    val count = portfolios.count { it.user_id == userId }
    return count
}

suspend fun getPortfoliosNames(userId: String): List<String> {
    Log.i("getPortfoliosNames", "Trying")

    return getPortfolios().filter { it.user_id == userId }.map { it.name }
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

