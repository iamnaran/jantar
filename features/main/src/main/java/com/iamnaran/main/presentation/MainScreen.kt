package com.iamnaran.main.presentation

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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamnaran.navigation.BottomNavScreens
import com.iamnaran.navigation.RootNavHost
import com.iamnaran.navigation.bar.AppBottomNavigation

@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun MainScreen() {

    val navController = rememberNavController()
    val topAppbarTitle = remember { mutableStateOf("") }
    val topAppBarState = rememberTopAppBarState()
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState)

    val showBottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val showTopBarState = rememberSaveable { (mutableStateOf(false)) }

    val currentScreen = remember { mutableStateOf(BottomNavScreens.Home.route) }
    val rootNavBackStackEntry by navController.currentBackStackEntryAsState()
    val coroutineScope = rememberCoroutineScope()


    when (rootNavBackStackEntry?.destination?.route) {
        BottomNavScreens.Home.route.toString() -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = BottomNavScreens.Home.name

        }

        BottomNavScreens.Explore.route.toString() -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = BottomNavScreens.Explore.name
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
            RootNavHost(navController)
        }
    }


}