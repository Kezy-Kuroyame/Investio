package com.example.empty_views_activity.moexAPI.APIClasses

data class SecuritiesModel(
    val tradeDate: String,
    val secID: String, // название
    val value: Double, // Стоимость всех акций проданных за день
    val volume: Int // количество акций  (value / volume = стоимость 1 акции)
)
