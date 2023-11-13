package com.example.empty_views_activity.screens

import android.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.R
import com.example.empty_views_activity.components.ButtonColorfulComponent
import com.example.empty_views_activity.components.ButtonWhiteComponent
import com.example.empty_views_activity.components.HeadingTextComponent
import com.example.empty_views_activity.components.TextFieldComponent
import com.example.empty_views_activity.navigation.Route
import com.example.empty_views_activity.ui.theme.colorCat
import com.example.empty_views_activity.ui.theme.colorSecondary

@Composable
fun LogInScreen(navController: NavController) {
    Surface(color = colorSecondary,
        modifier = Modifier
            .fillMaxSize()
            .background(colorSecondary)
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
        ){
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = R.drawable.cat_hand),
                    contentDescription = "cat",
                    modifier = Modifier.size(120.dp),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(colorCat)
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            HeadingTextComponent(value = stringResource(id = R.string.login))
            TextFieldComponent(labelValue = stringResource(id = R.string.email_hint),
                painterResource = painterResource(id = R.drawable.mail))
            Spacer(modifier = Modifier.height(20.dp))
            ButtonWhiteComponent(
                value = stringResource(id = R.string.login_in_button),
                route = Route.LoginInPassword.route,
                navController = navController
            )
            Spacer(modifier = Modifier.height(20.dp))
            ButtonColorfulComponent(
                value = stringResource(id = R.string.registration_text),
                route = Route.SignUp.route,
                navController = navController)

        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfLogInScreen(){
    LogInScreen(
        navController = rememberNavController()
    )
}