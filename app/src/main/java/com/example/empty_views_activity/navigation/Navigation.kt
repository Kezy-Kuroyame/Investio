package com.example.empty_views_activity.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.empty_views_activity.screens.LogInScreen
import com.example.empty_views_activity.screens.LogingPasswordScreen
import com.example.empty_views_activity.screens.SignUpScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.LoginIn.route,
        enterTransition = {
            // you can change whatever you want transition
            EnterTransition.None
        },
        exitTransition = {
            // you can change whatever you want transition
            ExitTransition.None
        }
    ){
        composable(route = Route.LoginIn.route){
            LogInScreen(navController)
        }
        composable(route = Route.LoginInPassword.route){
            LogingPasswordScreen(navController)
        }
        composable(route = Route.SignUp.route){
            SignUpScreen(navController)
        }
    }
}