package com.example.empty_views_activity.screens

import android.util.Log
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.ui.theme.colorSecondary
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.empty_views_activity.R

import com.example.empty_views_activity.modules.Stock
import com.example.empty_views_activity.queries.getStockByPortfolioId
import com.example.empty_views_activity.ui.theme.textColorWhite
import com.example.empty_views_activity.ui.theme.textHintColor
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


@Composable
fun PortfolioScreen(portfolioId: String, navController: NavController) {

    var userId by remember { mutableStateOf("") }
    var portfolioName by remember { mutableStateOf("") }
    var stocks by remember { mutableStateOf(emptyList<Stock>()) }
    var jsonList by remember { mutableStateOf(emptyList<Stock>()) }

    LaunchedEffect(Unit) {
        userId = "5"
        portfolioName = "Хуйня"
        jsonList= getStockByPortfolioId("3")
        stocks = Json.decodeFromString(jsonList.toString())
    }

    Surface(
        color = colorSecondary,
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(colorSecondary)

    ) {
        Column {
            Row {
                Box(modifier = androidx.compose.ui.Modifier.padding(0.dp, 10.dp)) {
                    ButtonBackToPortfolio(
                        route = "portfolio/${userId}",
                        navController = navController
                    )
                }
                IconButton(
                    onClick = { /* TODO add logic */ },
                    modifier = androidx.compose.ui.Modifier.padding(0.dp, 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add",
                        tint = textColorWhite,
                        modifier = androidx.compose.ui.Modifier.fillMaxSize()
                    )
                }

            }
            Row(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            ) {
                Card(
                    modifier = androidx.compose.ui.Modifier.fillMaxWidth()


                ) {
                    Box(
                        modifier = androidx.compose.ui.Modifier
                            .padding(10.dp, 10.dp)
                    ) {
                        Column {
                            Row {
                                Text(text = "Название портфеля:  ")
                                Text(text = "${portfolioName}")
                            }
                            Row {
                                Text(text = "Общая стоимость портфеля:  ")
                                Text(text = "1000 рублев")
                            }
                            Row {
                                Text(text = "Изменения за день: ")
                                Text(text = "500 рублев")
                            }
                        }

                    }
                }


            }
            LazyColumn {
                stocks.forEach { stock ->
                    println("Идентификатор: ${stock.id}")
                    println("Идентификатор портфеля: ${stock.id_portfolio}")
                    println("Идентификатор компании: ${stock.id_company}")
                    println("Количество: ${stock.amount}")
                    println("Название: ${stock.name}")
                    println("Текущая цена: ${stock.current_price}")
                    println("Цена покупки: ${stock.purchase_price}")
                }
            }
        }
    }
}
@Composable
fun StockCard(stock: Stock) {
    Card {
        Column {
            Text("Идентификатор: ${stock.id}")
            Text("Идентификатор портфеля: ${stock.id_portfolio}")
            Text("Идентификатор компании: ${stock.id_company}")
            Text("Количество: ${stock.amount}")
            Text("Название: ${stock.name}")
            Text("Текущая цена: ${stock.current_price}")
            Text("Цена покупки: ${stock.purchase_price}")
        }
    }
}


@Composable
fun MainItem(){

    Row(modifier = androidx.compose.ui.Modifier
        .fillMaxSize()
        .padding(30.dp)) {
        Card (modifier = androidx.compose.ui.Modifier


        ){
            Box(modifier = androidx.compose.ui.Modifier
                .padding(10.dp, 10.dp)){
                Column {
                    Row{
                        Text(text="Название портфеля:  ")
                        Text(text="1000 рублев")
                    }
                    Row{
                        Text(text="Общая стоимость портфеля:  ")
                        Text(text="1000 рублев")
                    }
                    Row{
                        Text(text="Изменения за день: ")
                        Text(text="500 рублев")
                    }
                }

            }
        }
        IconButton(
            onClick = { /* TODO add logic */ },
            modifier = androidx.compose.ui.Modifier.padding(0.dp, 5.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "add",
                tint = textColorWhite,
                modifier = androidx.compose.ui.Modifier.fillMaxSize()
            )
        }


    }
}



@Composable
fun ButtonBackToPortfolio(route: String, navController: NavController){
    Image(painter = painterResource(
        id = R.drawable.back),
        contentDescription = "back",
        modifier = androidx.compose.ui.Modifier
            .size(40.dp)
            .clickable { navController.navigate(route = route) },
        colorFilter = ColorFilter.tint(textHintColor)
    )
}

//@Preview
//@Composable
//fun PorfolioScreenPreview(){
//    PorfolioScreen( navController = rememberNavController(), "30")
//}
//class MyViewModel: ViewModel() {
//
//
//    private val _data = List<Stock>()
//    val data: kotlin.collections.emptyList<Stock> get() = _data
//
//    fun fetchData() {
//        viewModelScope.launch {
//            try {
//                val result: MutableList<Stock> = getStockByPortfolioId("3")
//                _data.value = result
//            } catch (e: Exception) {
//                Log.e("Stock/portfolio/id", "Пиздец ебаный")
//            }
//        }
//    }
//}