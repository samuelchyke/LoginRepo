package com.example.studentbeans.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(
    navBack :() -> Unit,
    ) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.primary,
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TopAppBar(
                title = {
                        Row(
                            modifier = Modifier.padding(start = 90.dp),
                        ) {
                            Text(text = "Photos") }

                },
                navigationIcon = {
                    IconButton(onClick = {navBack()}) {
                        Icon(Icons.Default.ArrowBack, "")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
            )
        }
    }
}

