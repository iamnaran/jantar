package com.iamnaran.navigation.nav

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

fun NavGraphBuilder.mainNavigationGraph(onNavigateBack: () -> Unit) {
    navigation<MainGraphRoute>(startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen() {
            }
        }
        composable<ExploreRoute> {
            ExploreScreen() {
                onNavigateBack()
            }
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



