package com.iamnaran.navigation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.iamnaran.camera.camera.navigation.cameraPreviewScreen
import com.iamnaran.info.navigation.infoScreen
import com.iamnaran.info.navigation.navigateToInfoScreen

@Composable
fun RootNavHost(navController: NavHostController) {

    NavHost(navController, startDestination = AuthGraphRoute) {
        cameraPreviewScreen {
            navController.navigateToInfoScreen(it)
        }
        infoScreen {
            navController.navigateUp()
        }

        authNavigationGraph {
            navController.navigateToMainGraphRoute()

        }

        mainNavigationGraph()

    }

}