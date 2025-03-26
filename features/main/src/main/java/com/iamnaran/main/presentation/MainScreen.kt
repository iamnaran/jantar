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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamnaran.navigation.NavScreen
import com.iamnaran.navigation.nav.RootNavHost
import com.iamnaran.navigation.bar.AppBottomNavigation
import com.iamnaran.navigation.nav.AuthGraphRoute
import com.iamnaran.navigation.nav.LoginRoute
import com.iamnaran.navigation.nav.RegisterRoute

@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun MainScreen() {

    val navController = rememberNavController()
    val topAppbarTitle = remember { mutableStateOf("") }
    val topAppBarState = rememberTopAppBarState()
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(state = topAppBarState)

    val showBottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val showTopBarState = rememberSaveable { (mutableStateOf(false)) }

    val rootNavBackStackEntry by navController.currentBackStackEntryAsState()
    val coroutineScope = rememberCoroutineScope()



    when (rootNavBackStackEntry?.destination?.route) {

        AuthGraphRoute::class.qualifiedName -> {
            showBottomBarState.value = false
            showTopBarState.value = false

        }

        LoginRoute::class.qualifiedName -> {
            showBottomBarState.value = false
            showTopBarState.value = false
        }

        RegisterRoute::class.qualifiedName -> {
            showBottomBarState.value = false
            showTopBarState.value = false
        }

        NavScreen.Home.route::class.qualifiedName -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = NavScreen.Home.name

        }

        NavScreen.Explore.route::class.qualifiedName -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = NavScreen.Explore.name
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
//            }
        },
        bottomBar = {
            if (showBottomBarState.value){
                AppBottomNavigation(navController = navController)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues).fillMaxSize()
        ) {
            RootNavHost(navController)
        }
    }


}