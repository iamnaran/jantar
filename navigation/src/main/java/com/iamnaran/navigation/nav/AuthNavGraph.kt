package com.iamnaran.navigation.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.iamnaran.auth.presentation.login.LoginScreen
import com.iamnaran.auth.presentation.register.RegisterScreen
import kotlinx.serialization.Serializable


@Serializable
data object AuthGraphRoute

@Serializable
data object LoginRoute

@Serializable
data object RegisterRoute

fun NavGraphBuilder.authNavigationGraph(onNavigateToMain: () -> Unit){
    navigation<AuthGraphRoute>(startDestination = LoginRoute){
        composable<LoginRoute> {
            LoginScreen(navigateToMain = {
                onNavigateToMain()
            }) {

            }
        }
        composable<RegisterRoute> {
            RegisterScreen(onRegisterSuccess = {

            }) {

            }
        }
    }
}