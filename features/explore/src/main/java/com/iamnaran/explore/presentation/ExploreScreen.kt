package com.iamnaran.explore.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExploreScreen(onNavigateBack: () -> Unit) {

    val exploreViewModel: ExploreViewModel = koinViewModel()

    val isLoggedOut = exploreViewModel.isLoggedOut.collectAsStateWithLifecycle()

    if (isLoggedOut.value) {
        onNavigateBack()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Button(onClick = {
            exploreViewModel.logout()
        }) {
            Text("Logout")
        }

    }

}