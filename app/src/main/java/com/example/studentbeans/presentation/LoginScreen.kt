package com.example.studentbeans.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(
    navController: NavController,
    emailText: String,
    passwordText: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onValidateFields: (NavController) -> Unit
) {
    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 50.dp,
                        vertical = 150.dp
                    )
            ) {
                Text(text = "Welcome back", fontSize = 30.sp)
                Text(text = "login to your student beans account")
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                )
                TextField(
                    value = emailText,
                    placeholder = { Text("Email") },
                    onValueChange = { email -> onEmailChanged(email) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                TextField(
                    value = passwordText,
                    placeholder = { Text("Password") },
                    onValueChange = { password -> onPasswordChanged(password) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
                Button(
                    onClick = { onValidateFields(navController) },
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
}