package com.example.studentbeans.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studentbeans.util.Screen
import com.example.studentbeans.viewmodel.MainViewModel

@Composable
fun Navigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                navController = navController,
                emailText = mainViewModel.email.value,
                passwordText = mainViewModel.password.value,
                onEmailChanged = mainViewModel::onEmailChanged,
                onPasswordChanged = mainViewModel::onPasswordChanged
            )
        }

        composable(route = Screen.MainScreen.route) {
            MainScreen(mainViewModel, navController)
        }
    }
}



