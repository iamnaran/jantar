package com.iamnaran.camera.camera.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.iamnaran.camera.camera.presentation.CameraPreviewScreen
import kotlinx.serialization.Serializable


@Serializable
data object CameraPreviewRoute

fun NavController.navigateToCameraPreviewScreen(navOptions: NavOptions? = null) {
    navigate(CameraPreviewRoute, navOptions)
}

fun NavGraphBuilder.cameraPreviewScreen(navigateToInfoScreen: (capturedUri: String) -> Unit ){
    composable<CameraPreviewRoute> {
        CameraPreviewScreen {
            navigateToInfoScreen(it.uriString.toString())
        }
    }
}



//@Serializable
//data object CameraPreviewNavGraph
//
//fun NavController.navigateToCameraPreviewNavGraph(navOptions: NavOptions? = null) {
//    navigate(CameraPreviewNavGraph, navOptions)
//}

