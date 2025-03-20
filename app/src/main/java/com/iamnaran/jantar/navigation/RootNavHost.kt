package com.iamnaran.jantar.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iamnaran.camera.camera.CameraPreviewScreen

@Composable
fun RootNavHost() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = CameraPreview) {
        composable<CameraPreview> {
            CameraPreviewScreen {

            }
        }

        composable<DetailScreen> { backStackEntry ->
//            val home = backStackEntry.toRoute<Home>()
//            HomeScreen(
//                bookId = home.userId,
//            )
        }
    }


}