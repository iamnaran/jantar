package com.iamnaran.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamnaran.camera.camera.navigation.cameraPreviewScreen
import com.iamnaran.camera.camera.navigation.navigateToCameraPreviewScreen
import com.iamnaran.explore.navigation.exploreScreen
import com.iamnaran.home.navigation.HomeRoute
import com.iamnaran.home.navigation.homeScreen
import com.iamnaran.info.navigation.infoScreen
import com.iamnaran.info.navigation.navigateToInfoScreen
import com.iamnaran.navigation.bar.AppTopBar
import com.iamnaran.navigation.nav.AppBottomNavigation
import com.iamnaran.navigation.nav.BottomScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavHost() {

    val navController = rememberNavController()
    val topAppbarTitle = remember { mutableStateOf("") }
    val topAppBarState = rememberTopAppBarState()
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState)

    val showBottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val showTopBarState = rememberSaveable { (mutableStateOf(false)) }

    val currentScreen = remember { mutableStateOf(BottomScreens.Home.route) }
    val rootNavBackStackEntry by navController.currentBackStackEntryAsState()
    val coroutineScope = rememberCoroutineScope()


    when (rootNavBackStackEntry?.destination?.route) {
        BottomScreens.Home.route.toString() -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = BottomScreens.Home.name

        }

        BottomScreens.Explore.route.toString() -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = BottomScreens.Explore.name
        }

        else -> {
            showBottomBarState.value = false
            showTopBarState.value = false
        }
    }


    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
//            AppTopBar("Jantar", barScrollBehavior, onActionCameraClick = {
//                navController.navigateToCameraPreviewScreen()
//            }){
//                // todo profile click
//            }
        },
        bottomBar = {
            AppBottomNavigation(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            NavHost(navController, startDestination = HomeRoute) {
                cameraPreviewScreen {
                    navController.navigateToInfoScreen(it)
                }
                infoScreen {
                    navController.navigateUp()
                }

                homeScreen {

                }

                exploreScreen {

                }

            }
        }
    }


}