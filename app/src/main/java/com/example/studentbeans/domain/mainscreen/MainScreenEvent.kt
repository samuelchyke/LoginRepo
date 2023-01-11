package com.example.studentbeans.domain.mainscreen

sealed class MainScreenEvent {

    class OnEmailChangedEvent(val email: String) : MainScreenEvent()

    class OnPasswordChangedEvent(val password: String) : MainScreenEvent()

    class OnLoginEvent(val navigateToMain: () -> Unit) : MainScreenEvent()

    object GetPhotosEvent : MainScreenEvent()
}
