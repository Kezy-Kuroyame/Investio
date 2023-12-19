package com.example.empty_views_activity.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.empty_views_activity.screens.LogInScreen
import com.example.empty_views_activity.screens.LogingPasswordScreen
import com.example.empty_views_activity.screens.PorfoliosScreen
import com.example.empty_views_activity.screens.PortfolioScreen
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

        composable(
            route = Route.LoginInPassword.route,
            arguments = listOf(
                navArgument("email"){
                    type = NavType.StringType
                }
            )
        ){
            val param = it.arguments?.getString("email") ?: ""
            LogingPasswordScreen(email = param, navController)
        }

        composable(route = Route.SignUp.route){
            SignUpScreen(navController)
        }

        composable(
            route = Route.Portfolios.route,
            arguments = listOf(
                navArgument("userId"){
                    type = NavType.StringType
                }
            )
        ){
            val param = it.arguments?.getString("userId") ?: ""
            PorfoliosScreen(userId = param, navController)
        }

        composable(
            route = Route.Portfolio.route,
            arguments = listOf(
                navArgument("portfolioId"){
                    type = NavType.StringType
                }
            )
        ){
            val param = it.arguments?.getString("portfolioId") ?: ""
            PortfolioScreen(portfolioId = param, navController)
        }
    }
}