package com.example.studentbeans.util

sealed class Screen(val route: String){
    object LoginScreen : Screen("LoginScreen")
    object MainScreen : Screen("MainScreen")
}