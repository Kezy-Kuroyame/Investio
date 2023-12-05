package com.example.empty_views_activity.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.R
import com.example.empty_views_activity.components.ButtonColorfulComponent
import com.example.empty_views_activity.components.LoginInButton
import com.example.empty_views_activity.components.HeadingTextComponent
import com.example.empty_views_activity.components.EmailFieldComponent
import com.example.empty_views_activity.navigation.Route
import com.example.empty_views_activity.ui.theme.colorCat
import com.example.empty_views_activity.ui.theme.colorSecondary


@Composable
fun LogInScreen(navController: NavController) {
    val emailValue = remember{
        mutableStateOf("")
    }


    Surface(color = colorSecondary,
        modifier = Modifier
            .fillMaxSize()
            .background(colorSecondary)
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
        ){
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = R.drawable.cat_hand),
                    contentDescription = "cat",
                    modifier = Modifier
                        .height(120.dp)
                        .width(135.dp),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(colorCat),
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            HeadingTextComponent(value = stringResource(id = R.string.login))
            EmailFieldComponent(labelValue = stringResource(id = R.string.email_hint),
                painterResource = painterResource(id = R.drawable.mail),
                rememberValue = emailValue)
            Spacer(modifier = Modifier.height(20.dp))
            LoginInButton(
                value = stringResource(id = R.string.login_in_button),
                route = Route.LoginInPassword.route,
                navController = navController,
                rememberValue = emailValue
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