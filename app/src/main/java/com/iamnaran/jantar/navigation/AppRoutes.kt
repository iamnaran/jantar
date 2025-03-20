package com.iamnaran.jantar.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.iamnaran.designsystem.AppIcons
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


@Serializable
data object CameraPreview

@Serializable
data class DetailScreen(val userId: String)


// Bottom Navigation

@Serializable
object HomeScreens

@Serializable
object ProfileScreen

@Serializable
object ExploreScreen

@Serializable
sealed class BottomScreens<T>(val name: String, val route: T, @Contextual val icon: ImageVector) {

    @Serializable
    data object Home :
        BottomScreens<HomeScreens>(
            name = "Profile",
            icon = AppIcons.HomeFilled,
            route = HomeScreens
        )

    @Serializable
    data object Profile : BottomScreens<ProfileScreen>(
        name = "Profile",
        icon = AppIcons.ProfileFilled,
        route = ProfileScreen
    )

    @Serializable
    data object Explore : BottomScreens<ExploreScreen>(
        name = "Job",
        icon = AppIcons.ProfileFilled,
        route = ExploreScreen
    )

}


// sealed class Destinations {
//    @Serializable
//    data object HomeGraph : Destinations()
//}
//enum class BottomNavigation(val label: String, val icon: ImageVector, val route: Destinations) {
//    HOME("Home", AppIcons, Destinations.Home),
//    PROFILE("Profile", AppIcons, Destinations.Profile);
//}

