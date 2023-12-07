package com.example.empty_views_activity.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.R
import com.example.empty_views_activity.components.ButtonBack
import com.example.empty_views_activity.components.HeadingTextComponent
import com.example.empty_views_activity.components.PasswordFieldComponent
import com.example.empty_views_activity.components.EmailFieldComponent
import com.example.empty_views_activity.components.SignUp_SignUpButton
import com.example.empty_views_activity.navigation.Route
import com.example.empty_views_activity.ui.theme.colorSecondary

@Composable
fun SignUpScreen (navController: NavController){
    val email = remember{
        mutableStateOf("")
    }
    val password1 = remember{
        mutableStateOf("")
    }
    val password2 = remember{
        mutableStateOf("")
    }

    Surface (
        color = colorSecondary,
        modifier = Modifier
            .fillMaxSize()
            .background(colorSecondary)

    ){
        Box (modifier = Modifier.padding(0.dp, 10.dp)) {
            ButtonBack(route = Route.LoginIn.route, navController = navController)
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)) {
            Spacer(modifier = Modifier.height(50.dp))
            HeadingTextComponent(value = stringResource(id = R.string.registration_text))
            EmailFieldComponent(
                labelValue = stringResource(id = R.string.email_hint),
                painterResource(id = R.drawable.mail),
                rememberValue = email
            )
            PasswordFieldComponent(
                labelValue = stringResource(id = R.string.input_password),
                painterResource = painterResource(id = R.drawable.lock),
                rememberValue = password1
            )
            PasswordFieldComponent(
                labelValue = stringResource(id = R.string.confirmed_password),
                painterResource = painterResource(id = R.drawable.lock),
                rememberValue = password2
            )
            Spacer(modifier = Modifier.height(30.dp))
            SignUp_SignUpButton(
                value = stringResource(id = R.string.registration_button),
                navController = navController,
                route = Route.LoginIn.route,
                rememberValues = mutableListOf(email, password1, password2)
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen(navController = rememberNavController())
}