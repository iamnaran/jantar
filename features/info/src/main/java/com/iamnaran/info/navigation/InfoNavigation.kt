package com.iamnaran.info.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.iamnaran.info.presentation.InfoScreen
import kotlinx.serialization.Serializable


@Serializable
data class InfoRoute(val captureImageUri: String)


fun NavController.navigateToInfoScreen(capturedUri: String, navOptions: NavOptions? = null) {
    navigate(InfoRoute(capturedUri.toString()), navOptions)
}

fun NavGraphBuilder.infoScreen(
    onNavigateBack: () -> Unit
) {
    composable<InfoRoute> { backStackEntry ->
        val detailScreen = backStackEntry.toRoute<InfoRoute>()
        InfoScreen(capturedImageUri = detailScreen.captureImageUri.toString())
    }
}

