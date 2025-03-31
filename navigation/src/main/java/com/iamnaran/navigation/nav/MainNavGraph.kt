package com.iamnaran.navigation.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.iamnaran.explore.presentation.ExploreScreen
import com.iamnaran.explore.presentation.videos.ForYouPageScreen
import com.iamnaran.home.presentation.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object MainGraphRoute

@Serializable
data object HomeRoute

@Serializable
data object ExploreRoute

@Serializable
data object ForYouRoute

fun NavGraphBuilder.mainNavigationGraph(onNavigateBack: () -> Unit) {
    navigation<MainGraphRoute>(startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(onNavigateBack = {}) {

            }
        }
        composable<ExploreRoute> {
            ExploreScreen() {
                onNavigateBack()
            }
        }

        composable<ForYouRoute> {
            ForYouPageScreen()
        }
    }
}


fun NavController.navigateToMainGraphRoute(navOptions: NavOptions? = null) {
    navigate(MainGraphRoute) {
        popUpTo(AuthGraphRoute) {
            inclusive = true
        }
        launchSingleTop = true
        navOptions?.let {

        }
    }
}

fun NavController.navigateToHomeRoute(navOptions: NavOptions? = null) {
    navigate(HomeRoute, navOptions)
}

fun NavController.navigateToExploreRoute(navOptions: NavOptions? = null) {
    navigate(ExploreRoute, navOptions)
}



