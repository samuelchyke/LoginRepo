package com.example.studentbeans.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studentbeans.domain.mainscreen.MainScreenEvent.*
import com.example.studentbeans.util.Screen
import com.example.studentbeans.domain.MainViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun Navigation(mainViewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {

        composable(route = Screen.LoginScreen.route) {
            val uiState = mainViewModel.uiState.collectAsState()
//            val email = mainViewModel.email.collectAsState()
//            val password = mainViewModel.password.collectAsState()
            LoginScreen(
                navController = navController,
                emailText = uiState.value.email,
                passwordText = uiState.value.password,
                onEmailChanged = { mainViewModel.onTriggerEvent( OnEmailChangedEvent(it)) },
                onPasswordChanged = { mainViewModel.onTriggerEvent( OnPasswordChangedEvent(it)) },
                onValidateFields = { mainViewModel.onTriggerEvent( OnValidateFieldsEvent(it)) }
            )
        }

        composable(route = Screen.MainScreen.route) {
            val uiState = mainViewModel.uiState.collectAsState()
            MainScreen(
                navController = navController,
                photos = uiState.value.photos
            )
        }
    }
}



