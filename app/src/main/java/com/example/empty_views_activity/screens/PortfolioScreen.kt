package com.example.empty_views_activity.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.components.ButtonAdd
import com.example.empty_views_activity.components.ButtonAddStock
import com.example.empty_views_activity.components.ButtonBackPortfolio
import com.example.empty_views_activity.components.ButtonLogOut
import com.example.empty_views_activity.components.HeaderPageText
import com.example.empty_views_activity.components.HeaderPortfolio
import com.example.empty_views_activity.components.Portfolio
import com.example.empty_views_activity.components.StockBlock
import com.example.empty_views_activity.modules.*
import com.example.empty_views_activity.queries.getPortfolioById
import com.example.empty_views_activity.queries.getPortfoliosNames
import com.example.empty_views_activity.queries.getStockByPortfolioId
import com.example.empty_views_activity.ui.theme.colorSecondary
import com.example.empty_views_activity.ui.theme.textHintColor
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun PortfolioScreen(portfolioId: String, navController: NavController){

    val stocks = remember {
        mutableStateOf(listOf<Stock>())
    }
    val portfolio = remember {
        mutableStateOf(Portfolio())
    }


    GlobalScope.launch(Dispatchers.IO) {
        stocks.value = getStockByPortfolioId(portfolioId)
        portfolio.value = getPortfolioById(portfolioId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorSecondary)

    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            ButtonBackPortfolio(portfolio.value.user_id, navController)
            HeaderPortfolio(portfolio.value)
            ButtonAddStock(portfolioId, stocks)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(color = textHintColor, thickness = 1.dp, modifier = Modifier.padding(horizontal = 0.dp))
        Log.i("ListPortfolios", stocks.toString())
        if (stocks.value.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(stocks.value){ item ->  StockBlock(item) }
            }
        }
    }
}

@Preview
@Composable
fun PortfolioScreenPreview(){
    PortfolioScreen("0", navController = rememberNavController())
}