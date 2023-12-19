package com.example.empty_views_activity.modules

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String? = null,
    val name: String,
    val email: String,
    val password: String, // password_id
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
    val user_id: String = "0",
    val name: String = "0",
    val price: Double = 0.0, // За сколько были куплены акции
    val total_profit: Double = 0.0, // Текущая цена с изменениями
    val profitability: Double = 0.0, // Насколько изменилась
    val change_day: Double = 0.0// Насколько поменялось за день
)


@Serializable
data class Company(
    val id: String? = null,
    val name : String,
    val current_price : Double
)

/** Юзер с паролём, а не с password_id */
@Serializable
data class UserDetails(
    val name: String,
    val email: String,
    val password: String,
    val create_date: String? = null
)
