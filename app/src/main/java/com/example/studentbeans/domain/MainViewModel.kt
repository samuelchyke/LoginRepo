package com.example.studentbeans.domain

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.studentbeans.domain.mainscreen.GetPhotosUseCase
import com.example.studentbeans.domain.mainscreen.MainScreenEvent
import com.example.studentbeans.domain.mainscreen.MainScreenEvent.*
import com.example.studentbeans.domain.mainscreen.MainScreenUIState
import com.example.studentbeans.model.PhotoItem
import com.example.studentbeans.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Saved instance state keys.
 */
private const val KEY_EMAIL = "email"
private const val KEY_PASSWORD = "password"

/**
 * UiState for the main screen.
 */
data class MainScreenUIState(
    val photos : List<PhotoItem> = listOf(),
    val email: String = "",
    val password: String = ""
)

/**
 * ViewModel for the main screen.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    // UI State
    private val _uiState = MutableStateFlow(MainScreenUIState())
    val  uiState = _uiState.asStateFlow()

//    val photos: MutableState<List<PhotoItem>> = mutableStateOf(listOf())

//    val email = savedStateHandle.getStateFlow(KEY_EMAIL, "")

//    val password = savedStateHandle.getStateFlow(KEY_PASSWORD, "")

    init {
        onTriggerEvent(GetPhotosEvent)

        _uiState.update {
            it.copy(
                email = savedStateHandle.getStateFlow(KEY_EMAIL,"").value,
                password = savedStateHandle.getStateFlow(KEY_PASSWORD,"").value
            )
        }
    }

    fun onTriggerEvent(event: MainScreenEvent){
        viewModelScope.launch {
            try {
                when(event){
                    is GetPhotosEvent -> {
                        getPhotos()
                    }
                    is OnEmailChangedEvent -> {
                        onEmailChanged(event.email)
                    }
                    is OnPasswordChangedEvent -> {
                        onPasswordChanged(event.password)
                    }
                    is OnValidateFieldsEvent -> {
                        onValidateFields(event.navController)
                    }
                }
            }catch (e: Exception){
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    private suspend fun getPhotos(){
//        photos.value = getPhotosUseCase.invoke()
        _uiState.update {
            it.copy(
                photos = getPhotosUseCase.invoke()
            )
        }
    }

    private fun onEmailChanged(email: String) {
        savedStateHandle[KEY_EMAIL] = email
        _uiState.update {
            it.copy(
                email = email
            )
        }
    }

    private fun onPasswordChanged(password: String) {
        savedStateHandle[KEY_PASSWORD] = password
        _uiState.update {
            it.copy(
                password = password
            )
        }
    }

    private fun onValidateFields(navController: NavController) {
//        val bothFieldsNotEmpty = password.value.isBlank() || email.value.isBlank()
        val bothFieldsNotEmpty = uiState.value.email.isBlank() || uiState.value.password.isBlank()
        if (bothFieldsNotEmpty) {
            Toast.makeText(
                navController.context,
                "Fill in both fields",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        navController.navigate(Screen.MainScreen.route)
    }

}

