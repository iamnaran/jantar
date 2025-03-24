package com.iamnaran.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.iamnaran.designsystem.theme.AppIcons
import com.iamnaran.navigation.graphs.ExploreRoute
import com.iamnaran.navigation.graphs.HomeRoute
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomNavScreens<T>(
    val name: String,
    val route: T,
    @Contextual val selectedIcon: ImageVector? = null,
    @Contextual val unSelectedIcon: ImageVector? = null,
) {
    data object Home : BottomNavScreens<HomeRoute>(
        "Home", HomeRoute,
        AppIcons.HomeFilled, AppIcons.HomeOutlined
    )

    data object Explore : BottomNavScreens<ExploreRoute>(
        "Explore", ExploreRoute,
        AppIcons.ExploreFilled, AppIcons.ExploreOutlined
    )
}