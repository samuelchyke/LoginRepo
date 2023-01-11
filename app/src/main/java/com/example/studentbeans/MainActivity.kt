package com.example.studentbeans

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.studentbeans.presentation.NavGraph
import com.example.studentbeans.ui.theme.StudentBeansTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity for the app
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentBeansTheme {
                NavGraph()
            }
        }
    }
}
