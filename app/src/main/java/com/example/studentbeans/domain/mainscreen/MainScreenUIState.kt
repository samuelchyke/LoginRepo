package com.example.studentbeans.domain.mainscreen

import androidx.compose.runtime.MutableState
import com.example.studentbeans.model.PhotoItem
import kotlinx.coroutines.flow.StateFlow

interface MainScreenUIState {
    // UI State
    val photos: MutableState<List<PhotoItem>>

    val email : StateFlow<String>

    val password : StateFlow<String>

}