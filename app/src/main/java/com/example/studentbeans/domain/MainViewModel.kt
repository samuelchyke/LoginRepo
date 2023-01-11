package com.example.studentbeans.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentbeans.domain.mainscreen.GetPhotosUseCase
import com.example.studentbeans.domain.mainscreen.MainScreenEvent
import com.example.studentbeans.domain.mainscreen.MainScreenEvent.*
import com.example.studentbeans.model.PhotoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * SavedInstanceState for the main screen
 */
private const val KEY_EMAIL = "email"
private const val KEY_PASSWORD = "password"

/**
 * UiState for the main screen.
 */
data class MainScreenUIState(
    val photos: List<PhotoItem> = listOf(),
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
) : ViewModel() {

    private val _photos: MutableStateFlow<List<PhotoItem>> = MutableStateFlow(listOf())
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

//    val uiState : StateFlow<MainScreenUIState> = combine(_photos,_email,_password)
//    { _photos,_email,_password ->
//        MainScreenUIState(
//            photos = _photos,
//            email = _email,
//            password = _password
//        )
//    }.stateIn(
//        viewModelScope,
//        SharingStarted.WhileSubscribed(),
//        MainScreenUIState(email = "")
//    )
    private val _uiState = MutableStateFlow(MainScreenUIState())
    val uiState = _uiState.asStateFlow()

    init {
        onTriggerEvent(GetPhotosEvent)

//        _uiState.update {
//            it.copy(
//                email = savedStateHandle.getStateFlow(KEY_EMAIL, "").value,
//                password = savedStateHandle.getStateFlow(KEY_PASSWORD, "").value
//            )
//        }
    }

    fun onTriggerEvent(event: MainScreenEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GetPhotosEvent -> {
                        getPhotos()
                    }
                    is OnEmailChangedEvent -> {
                        onEmailChanged(event.email)
                    }
                    is OnPasswordChangedEvent -> {
                        onPasswordChanged(event.password)
                    }
                    is OnLoginEvent -> {
                        onLogin(event.navigateToMain)
                    }
                }
            } catch (e: Exception) {
                Timber.e("launchJob: Exception: $e, ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    private suspend fun getPhotos() {
        getPhotosUseCase.invoke().data?.let { photos ->
            _photos.value = photos
            _uiState.update {
                it.copy(
                    photos = photos
                )
            }
        }
    }

    private fun onEmailChanged(email: String) {
        savedStateHandle[KEY_EMAIL] = email
        _email.value = email

        _uiState.update {
            it.copy(
                email = email
            )
        }
    }

    private fun onPasswordChanged(password: String) {
        savedStateHandle[KEY_PASSWORD] = password
        _password.value = password
        _uiState.update {
            it.copy(
                password = password
            )
        }
    }

    private fun onLogin(navigateToMain: () -> Unit) {
        if (validateFields()) {
            return
        }
        navigateToMain.invoke()
    }

    private fun validateFields(): Boolean {
        return _email.value.isBlank() || _password.value.isBlank()
    }
}
