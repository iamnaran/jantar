package com.iamnaran.jantar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.iamnaran.camera.camera.CameraPreviewScreen
import com.iamnaran.info.DetailInfoScreen

@Composable
fun RootNavHost() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = CameraPreview) {
        composable<CameraPreview> {
            CameraPreviewScreen {
                navController.navigate(DetailScreen(it.uriString))
            }
        }

        composable<DetailScreen> { backStackEntry ->
            val detailScreen = backStackEntry.toRoute<DetailScreen>()
            DetailInfoScreen(capturedImageUri = detailScreen.captureImageUri.toString())
        }
    }


}