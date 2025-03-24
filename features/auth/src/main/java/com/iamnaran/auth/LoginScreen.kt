package com.iamnaran.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.iamnaran.designsystem.R.*

@Composable
fun LoginScreen() {

    Column(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {

            Image(
                painter = painterResource(id = drawable.jantar_app_logo),
                contentDescription = "Logo"
            )

        }

        Box(modifier = Modifier.weight(2f), contentAlignment = Alignment.Center) {




        }

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {



        }

    }

}