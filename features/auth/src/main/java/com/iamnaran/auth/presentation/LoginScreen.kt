package com.iamnaran.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.iamnaran.designsystem.R.drawable

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {

            Image(
                painter = painterResource(id = drawable.jantar_app_logo),
                contentDescription = "Logo"
            )

        }

        Box(modifier = Modifier.weight(2f), contentAlignment = Alignment.Center) {


            Column {


                Button(onClick = onLoginSuccess) {
                    Text(text = "Login")
                }

            }


        }

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {



        }

    }

}