package com.chtan.miniworld.presentation.user.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.chtan.miniworld.Route

@Composable
fun SignInScreen(
    events: (event: SignInEvent) -> Unit,
    states: SignInState,
    nav: NavHostController
){
    Scaffold(
        content = {

            LaunchedEffect(states.isLoggedIn){
                if (states.isLoggedIn){
                    nav.navigate(Route.Derive)
                }

            }
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp)
            ) {

                SignIn(onClick = { email, password ->
                    events(SignInEvent.SignInAction(email,password))
                },
                    onForgetPassword = {})
            }


        }
    )


}

@Composable
fun SignIn(
    onClick: (email: String, password: String) -> Unit,
    onForgetPassword: () -> Unit
) {
    var email by remember { mutableStateOf("chetan@gmail.com") }
    var password by remember { mutableStateOf("Nepal123") }

    Column(
        modifier = Modifier.padding(horizontal = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.End
    ) {
        TextField(modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                email = it
            },
            value = email,
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            singleLine = true,
            placeholder = {
                Text("...@gmail.com")
            },
            label = { Text("Email Addressuwa") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,

                )
        )


        TextField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                password = it
            },
            value = password,
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            singleLine = true,
            placeholder = {
                Text("*******")
            },
            label = { Text("Password") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,

                ),
        )
        TextButton(onClick = {
            onForgetPassword()
        }) {
            Text("Password?", fontWeight = FontWeight.ExtraBold)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp), onClick = {
            onClick(email, password)
        }) {
            Text("Login", modifier = Modifier.padding(3.dp), fontWeight = FontWeight.SemiBold)
        }

    }
}

