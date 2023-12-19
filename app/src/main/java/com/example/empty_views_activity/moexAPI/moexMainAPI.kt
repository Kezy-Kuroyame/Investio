package com.example.empty_views_activity.moexAPI
import com.example.empty_views_activity.moexAPI.APIClasses.BankFinancialStatement
import com.example.empty_views_activity.moexAPI.APIClasses.OtherFinancialStatement
import com.example.empty_views_activity.moexAPI.APIClasses.SecuritiesModel
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import org.json.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


private fun getAPIstring(url: String): String? {
    val conn = URL(url).openConnection() as HttpURLConnection
    conn.requestMethod = "GET"
    var res: String?
    res = ""

    BufferedReader(InputStreamReader(conn.inputStream)).use { br ->
        var line: String?
        while (br.readLine().also { line = it } != null) {
            res += line + '\n'
        }
    }
    return res
}

fun getFinancialStatementModel(secID: String): Any{
    val doc = Jsoup.connect("https://smart-lab.ru/q/" + secID +"/f/y/MSFO/").get()
    if (financialStatementsCheck(doc)){
        return getBankFinancialStatement(doc)
    }
    else{
        return getOtherFinancialStatement(doc)
    }
}

fun getSecuritiesModel(tradeData: String, secID: String): SecuritiesModel {
    val res = getAPIstring("https://iss.moex.com/iss/securities/" + secID + "/aggregates.json?date=" + tradeData)
    val mainObject = JSONObject(res.toString())

    return SecuritiesModel(
        mainObject.getJSONObject("aggregates").getJSONArray("data").getJSONArray(0).getString(3),
        mainObject.getJSONObject("aggregates").getJSONArray("data").getJSONArray(0).getString(4),
        mainObject.getJSONObject("aggregates").getJSONArray("data").getJSONArray(0).getDouble(5),
        mainObject.getJSONObject("aggregates").getJSONArray("data").getJSONArray(0).getInt(6)
    )
}

private fun getBankFinancialStatement(doc: Document): BankFinancialStatement {
    return BankFinancialStatement(
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"capital\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"net_operating_income\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"net_income\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"div_yield\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"div_yield_priv\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"div_payout_ratio\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"p_e\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"p_b\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"net_intertest_margin\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"roe\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"roa\"] td"))
    )
}

private fun getOtherFinancialStatement(doc: Document): OtherFinancialStatement {
    return OtherFinancialStatement(
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"market_cap\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"ev\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"revenue\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"net_income\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"div_yield\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"div_payout_ratio\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"p_e\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"p_s\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"p_bv\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"ev_ebitda\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"ebitda_margin\"] td")),
        regexFinancialStatement(doc.select(".simple-little-table tbody tr[field=\"debt_ebitda\"] td"))
    )
}

private fun regexFinancialStatement(list: ArrayList<Element>): Float{
    val str = list.get(7).text()
    if (str == ""){
        return Float.NaN
    }
    return str.replace(" ", "").replace("%", "").toFloat()
}

private fun financialStatementsCheck(doc: Document): Boolean{
    return doc.select(".simple-little-table tbody tr[field=\"ebitda_margin\"] td").size == 0
}

// Example to use
//
// getSecuritiesModel(2022-09-21, "YNDX")
// SecuritiesModel(tradeDate=2022-09-21, secID=YNDX, value=4.17317171E9, volume=2225489)
//
// getFinancialStatementModel("YNDX")
// OtherFinancialStatements(capital=809.0, eV=829.8, revenue=643.7, netProfit=17.9, preferredShareDividendYield=0.0, dividendPayoutRatio=0.0, pE=45.1, pS=1.26, pB=6.97, evEBITDA=11.1, ebitdaMargin=11.6, debtEBITDA=0.28)