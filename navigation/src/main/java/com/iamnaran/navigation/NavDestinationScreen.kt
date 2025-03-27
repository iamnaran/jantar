package com.iamnaran.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.iamnaran.designsystem.theme.AppIcons
import com.iamnaran.navigation.nav.ExploreRoute
import com.iamnaran.navigation.nav.HomeRoute
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class NavDestinationScreen<T>(
    val name: String,
    val route: T,
    @Contextual val selectedIcon: ImageVector? = null,
    @Contextual val unSelectedIcon: ImageVector? = null,
) {
    data object Home : NavDestinationScreen<HomeRoute>(
        "Home", HomeRoute,
        AppIcons.HomeFilled, AppIcons.HomeOutlined
    )

    data object Explore : NavDestinationScreen<ExploreRoute>(
        "Explore", ExploreRoute,
        AppIcons.ExploreFilled, AppIcons.ExploreOutlined
    )
}