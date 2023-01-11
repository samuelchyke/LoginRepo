package com.example.studentbeans.presentation.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.studentbeans.model.PhotoItem

@Composable
fun PhotoList(
    photos: List<PhotoItem>
) {
    Box(
        modifier = Modifier.background(color = MaterialTheme.colors.surface)
    ) {
        LazyColumn {
            itemsIndexed(
                items = photos
            ) { _, photos ->
                PhotoCard(
                    photo = photos,
                )
            }
        }
    }
}
