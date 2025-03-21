package com.iamnaran.jantar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.iamnaran.camera.camera.navigation.CameraPreviewRoute
import com.iamnaran.camera.camera.navigation.cameraPreviewScreen
import com.iamnaran.info.navigation.infoScreen
import com.iamnaran.info.navigation.navigateToInfoScreen

@Composable
fun RootNavHost() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = CameraPreviewRoute) {
        cameraPreviewScreen{
            navController.navigateToInfoScreen(it.toString())
        }
        infoScreen {
            navController.popBackStack()
        }
    }

}