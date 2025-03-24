package com.iamnaran.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.iamnaran.auth.presentation.LoginScreen
import com.iamnaran.auth.presentation.RegisterScreen
import kotlinx.serialization.Serializable


@Serializable
data object AuthGraphRoute

@Serializable
data object LoginRoute

@Serializable
data object RegisterRoute


fun NavGraphBuilder.authNavigationGraph(onAuthenticationSuccess: () -> Unit){

    navigation<AuthGraphRoute>(startDestination = LoginRoute){
        composable<LoginRoute> {
            LoginScreen() {
                onAuthenticationSuccess()
            }
        }

        composable<RegisterRoute> {
            RegisterScreen(onRegisterSuccess = {

            }) {

            }
        }

    }


}