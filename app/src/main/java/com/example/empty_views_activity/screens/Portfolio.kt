package com.example.empty_views_activity.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.ui.theme.colorSecondary
import java.lang.reflect.Modifier
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp

import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.R
import com.example.empty_views_activity.components.ButtonAdd
import com.example.empty_views_activity.components.ButtonBack
import com.example.empty_views_activity.components.ButtonLogOut
import com.example.empty_views_activity.components.EmailFieldComponent
import com.example.empty_views_activity.components.HeaderPageText
import com.example.empty_views_activity.components.HeadingTextComponent
import com.example.empty_views_activity.components.PasswordFieldComponent
import com.example.empty_views_activity.components.Portfolio
import com.example.empty_views_activity.components.SignUp_SignUpButton
import com.example.empty_views_activity.navigation.Route
import com.example.empty_views_activity.queries.countUsersPortfolio
import com.example.empty_views_activity.queries.getPortfoliosNames
import com.example.empty_views_activity.ui.theme.colorPrimary
import com.example.empty_views_activity.ui.theme.colorPurple
import com.example.empty_views_activity.ui.theme.colorSecondary
import com.example.empty_views_activity.ui.theme.colorTintPink
import com.example.empty_views_activity.ui.theme.textColorWhite
import com.example.empty_views_activity.ui.theme.textHintColor
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun PorfolioScreen(navController: NavController) {
    Surface(
        color = colorSecondary,
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(colorSecondary)

    ) {
        Column {
            Row {
                Box(modifier = androidx.compose.ui.Modifier.padding(0.dp, 10.dp)) {
                    ButtonBack(route = Route.LoginIn.route, navController = navController)
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
            Column(modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(30.dp)) {
                Card (modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()

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

            }



        }
    }
}
@Composable
fun MainItem(){
    Column(modifier = androidx.compose.ui.Modifier
        .fillMaxSize()
        .padding(30.dp)) {
        Card (modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
        ){
            Box(modifier = androidx.compose.ui.Modifier
                .padding(0.dp, 10.dp)){
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

    }
}

@Preview
@Composable
fun PorfolioScreenPreview(){
    PorfolioScreen( navController = rememberNavController())
}