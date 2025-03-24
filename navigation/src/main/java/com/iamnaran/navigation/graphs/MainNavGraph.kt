package com.iamnaran.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.iamnaran.explore.presentation.ExploreScreen
import com.iamnaran.home.presentation.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data object MainGraphRoute

@Serializable
data object HomeRoute

@Serializable
data object ExploreRoute

fun NavGraphBuilder.mainNavigationGraph() {

    navigation<MainGraphRoute>(startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen() {

            }
        }

        composable<ExploreRoute> {
            ExploreScreen() {

            }
        }

    }
}


fun NavController.navigateToMainGraphRoute(navOptions: NavOptions? = null) {
    navigate(MainGraphRoute, navOptions)
}

fun NavController.navigateToHomeRoute(navOptions: NavOptions? = null) {
    navigate(HomeRoute, navOptions)
}

fun NavController.navigateToExploreRoute(navOptions: NavOptions? = null) {
    navigate(ExploreRoute, navOptions)
}



