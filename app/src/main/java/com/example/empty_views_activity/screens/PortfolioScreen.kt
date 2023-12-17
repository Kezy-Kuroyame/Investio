package com.example.empty_views_activity.screens

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

import com.example.empty_views_activity.R
import com.example.empty_views_activity.ui.theme.textColorWhite
import com.example.empty_views_activity.ui.theme.textHintColor

@Composable
fun PorfolioScreen(navController: NavController, param: String) {

    var userId by remember { mutableStateOf("") }
    var portfolioName by remember { mutableStateOf("") }

    // Создайте эффект для выполнения запроса при первом запуске компонента
    LaunchedEffect(Unit) {
        // Выполните запрос к серверу для получения id_user

//        var portfolio = getPortfolioById(param)
        userId="5"
        portfolioName="Хуйня"
//        var stock = getStockByPortfolioId()
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
                    ButtonBackToPortfolio(route = "portfolio/${userId}", navController = navController)
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
            Row(modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(30.dp)) {
                Card (modifier = androidx.compose.ui.Modifier.fillMaxWidth()


                ){
                    Box(modifier = androidx.compose.ui.Modifier
                        .padding(10.dp, 10.dp)){
                        Column {
                            Row{
                                Text(text="Название портфеля:  ")
                                Text(text="${portfolioName}")
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


            }
//            LazyColumn (modifier = androidx.compose.ui.Modifier.fillMaxSize()){
//                GlobalScope.launch(Dispatchers.IO) {
//                    val names: List<String> = getPortfoliosNames(param)
//                    items(names.size) { index ->
//                        Portfolio(value = names[index], route = names[index], navController = navController)
//                    }
//                }
//            }


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

@Preview
@Composable
fun PorfolioScreenPreview(){
    PorfolioScreen( navController = rememberNavController(), "30")
}