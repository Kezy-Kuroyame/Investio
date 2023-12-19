package com.example.empty_views_activity.moexAPI.APIClasses

data class BankFinancialStatement(
    val capital: Float,
    val netOperatingIncome: Float,
    val netProfit: Float,
    val ordinaryShareDividendYield: Float,
    val preferredShareDividendYield: Float,
    val dividendPayoutRatio: Float,
    val pE: Float,
    val pB: Float,
    val netIntertestMargin: Float,
    val rOE: Float,
    val rOA: Float
)