package com.example.studentbeans.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.studentbeans.presentation.composables.AppBar
import com.example.studentbeans.presentation.composables.PhotoList
import com.example.studentbeans.util.Screen
import com.example.studentbeans.viewmodel.MainViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            AppBar {
                navController.navigate(Screen.LoginScreen.route)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            PhotoList(
                photos = mainViewModel.photos.value
            )
        }
    }
}