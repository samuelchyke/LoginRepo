package com.example.studentbeans.domain.mainscreen

import androidx.navigation.NavController

sealed class MainScreenEvent {

    class OnEmailChangedEvent(val email : String) : MainScreenEvent()

    class OnPasswordChangedEvent (val password : String) : MainScreenEvent()

    class OnValidateFieldsEvent (val navController : NavController): MainScreenEvent()

    object GetPhotosEvent : MainScreenEvent()

}