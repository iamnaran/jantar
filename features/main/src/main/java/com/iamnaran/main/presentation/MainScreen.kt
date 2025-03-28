package com.iamnaran.main.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iamnaran.navigation.NavDestinationScreen
import com.iamnaran.navigation.bar.AppBottomNavigation
import com.iamnaran.navigation.bar.FancyAppBar
import com.iamnaran.navigation.nav.AuthGraphRoute
import com.iamnaran.navigation.nav.LoginRoute
import com.iamnaran.navigation.nav.RegisterRoute
import com.iamnaran.navigation.nav.RootNavHost
import org.koin.androidx.compose.koinViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun MainScreen(isLoggedIn: Boolean) {

    val viewModel: MainViewModel = koinViewModel()

    val navController = rememberNavController()
    val topAppbarTitle = remember { mutableStateOf("") }
    val topAppBarState = rememberTopAppBarState()
    val barScrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(state = topAppBarState)

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

        NavDestinationScreen.Home.route::class.qualifiedName -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = NavDestinationScreen.Home.name

        }

        NavDestinationScreen.Explore.route::class.qualifiedName -> {
            showBottomBarState.value = true
            showTopBarState.value = true
            topAppbarTitle.value = NavDestinationScreen.Explore.name
        }

        else -> {
            showBottomBarState.value = false
            showTopBarState.value = false
        }
    }


    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(barScrollBehavior.nestedScrollConnection),
        topBar = {
            FancyAppBar(topAppbarTitle.value, barScrollBehavior)
        },
        bottomBar = {
            if (showBottomBarState.value) {
                AppBottomNavigation(navController = navController)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            RootNavHost(isLoggedIn = isLoggedIn, navController)
        }
    }


}