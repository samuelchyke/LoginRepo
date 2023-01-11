package com.example.studentbeans.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.studentbeans.domain.MainViewModel
import com.example.studentbeans.domain.mainscreen.MainScreenEvent

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    mainViewModel: MainViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
    ) { paddingValues ->

        val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
        LoginScreenContent(
            modifier = Modifier.padding(paddingValues),
            emailText = uiState.email,
            passwordText = uiState.password,
            onEmailChanged = { mainViewModel.onTriggerEvent(MainScreenEvent.OnEmailChangedEvent(it)) },
            onPasswordChanged = { mainViewModel.onTriggerEvent(MainScreenEvent.OnPasswordChangedEvent(it)) },
            onLogin = { mainViewModel.onTriggerEvent(MainScreenEvent.OnLoginEvent(onLogin)) },
        )
    }
}

@Composable
private fun LoginScreenContent(
    modifier: Modifier = Modifier,
    emailText: String,
    passwordText: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLogin: () -> Unit,
) {
    val screenPadding = Modifier.padding(
        horizontal = 50.dp,
        vertical = 150.dp,
    )
    val commonModifier = modifier
        .fillMaxWidth()
        .then(screenPadding)
    Surface(
        modifier = commonModifier,
        color = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Welcome back", fontSize = 30.sp)
            Text(text = "login to your account")
            Spacer(modifier = Modifier.fillMaxWidth().padding(15.dp))
            TextField(
                value = emailText,
                placeholder = { Text("Email") },
                onValueChange = { email -> onEmailChanged(email) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.fillMaxWidth().padding(15.dp))
            TextField(
                value = passwordText,
                placeholder = { Text("Password") },
                onValueChange = { password -> onPasswordChanged(password) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.fillMaxWidth().padding(5.dp))
            Button(
                onClick = { onLogin() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
}
