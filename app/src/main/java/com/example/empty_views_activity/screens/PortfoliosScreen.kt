package com.example.empty_views_activity.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.components.ButtonAdd
import com.example.empty_views_activity.components.ButtonLogOut
import com.example.empty_views_activity.components.HeaderPageText
import com.example.empty_views_activity.components.Portfolio
import com.example.empty_views_activity.navigation.Route
import com.example.empty_views_activity.queries.countUsersPortfolio
import com.example.empty_views_activity.queries.getPortfoliosNames
import com.example.empty_views_activity.ui.theme.colorPrimary
import com.example.empty_views_activity.ui.theme.colorPurple
import com.example.empty_views_activity.ui.theme.colorSecondary
import com.example.empty_views_activity.ui.theme.colorTintPink
import com.example.empty_views_activity.ui.theme.textHintColor
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun PorfoliosScreen(userId: String, navController: NavController){

    val portfolios = remember {
        mutableStateOf(listOf<String>())
    }

    GlobalScope.launch(Dispatchers.IO) {
        portfolios.value = getPortfoliosNames(userId)
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
            ButtonLogOut(navController)
            HeaderPageText(value = "Портфели")
            ButtonAdd(userId, navController, portfolios)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(color = textHintColor, thickness = 1.dp, modifier = Modifier.padding(horizontal = 0.dp))
        Log.i("ListPortfolios", portfolios.toString())
        if (portfolios.value.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(portfolios.value){ item ->  Portfolio(value = item, navController = navController)}
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
fun PorfoliosScreenPreview(){
    PorfoliosScreen("31", navController = rememberNavController())
}