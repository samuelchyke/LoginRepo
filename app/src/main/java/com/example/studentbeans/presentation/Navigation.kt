package com.example.studentbeans.presentation

import androidx.navigation.NavHostController
import com.example.studentbeans.presentation.Destinations.LOGIN_ROUTE
import com.example.studentbeans.presentation.Destinations.MAIN_SCREEN_ROUTE

/**
 * Screens used in [Destinations]
 */
sealed class Screens(val route: String) {
    object LoginScreen : Screens("LoginScreen")
    object MainScreen : Screens("MainScreen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}

/**
 * [Destinations] used in the [NavigationActions]
 */
object Destinations {
    val LOGIN_ROUTE = Screens.LoginScreen.route
    val MAIN_SCREEN_ROUTE = Screens.MainScreen.route
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(private val navController: NavHostController) {

    fun navigateToLogin() {
        navController.navigate(LOGIN_ROUTE)
    }

    fun navigateToMainScreen() {
        navController.navigate(MAIN_SCREEN_ROUTE)
    }
}
