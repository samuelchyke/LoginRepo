package com.example.studentbeans.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.studentbeans.model.PhotoItem
import com.example.studentbeans.presentation.mainscreen.AppBar
import com.example.studentbeans.presentation.mainscreen.PhotoList
import com.example.studentbeans.util.Screen

@Composable
fun MainScreen(
    navController: NavController,
    photos : List<PhotoItem>
) {
    Scaffold(
        topBar = {
            AppBar (
                navBack = { navController.navigate(Screen.LoginScreen.route) }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            PhotoList(
                photos = photos
            )
        }
    }
}





