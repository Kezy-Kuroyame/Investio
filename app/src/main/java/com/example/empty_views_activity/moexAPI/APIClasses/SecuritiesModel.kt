package com.example.empty_views_activity.moexAPI.APIClasses

data class SecuritiesModel(
    val tradeDate: String,
    val secID: String,
    val value: Double,
    val volume: Int
)
