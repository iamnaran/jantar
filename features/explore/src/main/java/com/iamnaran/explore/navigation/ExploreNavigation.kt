package com.iamnaran.explore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.iamnaran.explore.presentation.ExploreScreen
import kotlinx.serialization.Serializable

@Serializable
data object ExploreRoute

fun NavController.navigateToExploreRoute(navOptions: NavOptions? = null) {
    navigate(ExploreRoute, navOptions)
}

fun NavGraphBuilder.exploreScreen(onNavigateBack: () -> Unit ){
    composable<ExploreRoute> {
        ExploreScreen() {

        }
    }
}