package com.iamnaran.navigation.nav

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.iamnaran.designsystem.theme.AppIcons
import com.iamnaran.explore.navigation.ExploreRoute
import com.iamnaran.home.navigation.HomeRoute
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomScreens<T>(
    val name: String,
    val route: T,
    @Contextual val selectedIcon: ImageVector? = null,
    @Contextual val unSelectedIcon: ImageVector? = null,
) {
    data object Home : BottomScreens<HomeRoute>(
        "Home", HomeRoute,
        AppIcons.HomeFilled, AppIcons.HomeOutlined
    )

    data object Explore : BottomScreens<ExploreRoute>(
        "Explore", ExploreRoute,
        AppIcons.ExploreFilled, AppIcons.ExploreOutlined
    )
}


@Composable
fun AppBottomNavigation(navController: NavController) {

    val bottomScreens = remember {
        listOf(
            BottomScreens.Home,
            BottomScreens.Explore,
        )
    }

    NavigationBar {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomScreens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route::class.qualifiedName } == true
            NavigationBarItem(
                selected = isSelected,
                label = {
                    Text(screen.name)
                },
                icon = {
                    BadgedBox(badge = {
                        Badge {
                            Text(text = "8")
                        }
                    }) {

                    }
                    Icon(
                        imageVector = if (isSelected) screen.selectedIcon!! else screen.unSelectedIcon!!,
                        contentDescription = screen.name
                    )
                },

                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )

        }

    }

}