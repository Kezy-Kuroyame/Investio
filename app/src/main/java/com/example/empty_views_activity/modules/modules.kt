package com.example.empty_views_activity.modules

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String? = null,
    val name: String,
    val email: String,
    val password: String,
    val create_date: String? = null
)

@Serializable
data class Stock(
    val id: String? = null,
    val id_portfolio: String,
    val id_company: String,
    val amount: Int,
    val name : String,
    val current_price: Double,
    val purchase_price: Double
)

@Serializable
data class Portfolio (
    val id: String? = null,
    val user_id: String,
    val name: String,
    val price: Double,
    val total_profit: Double,
    val profitability: Double,
    val change_day: Double
)

@Serializable
data class Company(
    val id: String? = null,
    val name : String,
    val current_price : Double
)