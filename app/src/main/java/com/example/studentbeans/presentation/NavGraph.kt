package com.example.studentbeans.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studentbeans.presentation.Destinations.LOGIN_ROUTE
import com.example.studentbeans.presentation.Destinations.MAIN_SCREEN_ROUTE

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = LOGIN_ROUTE,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = LOGIN_ROUTE) {
            LoginScreen(
                onLogin = { navActions.navigateToMainScreen() }
            )
        }
        composable(route = MAIN_SCREEN_ROUTE) {
            MainScreen(
                onBack = { navActions.navigateToLogin() }
            )
        }
    }
}
