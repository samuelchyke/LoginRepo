package com.example.studentbeans.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentbeans.model.PhotoItem
import com.example.studentbeans.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val photos: MutableState<List<PhotoItem>> = mutableStateOf(listOf())

    val email = mutableStateOf("")

    val password = mutableStateOf("")

    init {
        searchPhotos()
    }

    fun searchPhotos(){
        safeSearchPhotos()
    }

    private fun safeSearchPhotos() = viewModelScope.launch {
        try {
            val response = networkRepository.getListOfPhotos()
            response.body()?.let {
                photos.value = it
            }
        }catch (e: Exception){
            Log.d(TAG, "An error has occurred: $e")
        }
    }

    fun onEmailChanged(email: String) {
        this.email.value = email
    }

    fun onPasswordChanged(password: String) {
        this.password.value = password
    }

}

