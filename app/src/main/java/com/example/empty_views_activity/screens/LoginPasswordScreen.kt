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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.R
import com.example.empty_views_activity.components.HeadingTextComponent
import com.example.empty_views_activity.components.*
import com.example.empty_views_activity.components.NormalTextComponent
import com.example.empty_views_activity.navigation.Route
import com.example.empty_views_activity.ui.theme.colorSecondary


@Composable
fun LogingPasswordScreen(navController: NavController) {
    Surface(color = colorSecondary,
        modifier = Modifier
            .fillMaxSize()
            .background(colorSecondary)
    ){
        Box (modifier = Modifier.padding(0.dp, 10.dp)) {
            ButtonBack(route = Route.LoginIn.route, navController = navController)
        }
      Column (modifier = Modifier
          .fillMaxSize()
          .padding(30.dp)){
          Spacer(modifier = Modifier.height(80.dp))
          HeadingTextComponent(value = stringResource(id = R.string.enter_password))
          HintTextComponent(value = stringResource(id = R.string.enter_password_hint))
          NormalTextComponent(value = stringResource(id = R.string.example_email))
          Spacer(modifier = Modifier.height(15.dp))
          PasswordFieldComponent(labelValue = stringResource(id = R.string.input_password),
              painterResource = painterResource(id = R.drawable.lock))
          Spacer(modifier = Modifier.height(20.dp))
          ButtonColorfulComponent(
              value = stringResource(id = R.string.login_in_button),
              navController = navController,
              route = Route.LoginIn.route
          )
      }
    }
}

@Preview
@Composable
fun DefaultPreviewLogingPasswordScreen(){
    LogingPasswordScreen(navController = rememberNavController())
}