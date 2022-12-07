package com.example.studentbeans

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.studentbeans.presentation.Navigation
import com.example.studentbeans.ui.theme.StudentBeansTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentBeansTheme {
                Navigation()
            }
        }
    }
}

