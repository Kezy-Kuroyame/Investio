package com.example.empty_views_activity.navigation

sealed class Route(val route: String) {
    data object LoginIn: Route(route = "login_in_screen")
    data object LoginInPassword: Route(route = "login_in_password_screen")
    data object SignUp: Route(route = "sign_up_screen")
}