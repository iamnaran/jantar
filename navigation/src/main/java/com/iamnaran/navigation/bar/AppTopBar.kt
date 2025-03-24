package com.iamnaran.navigation.bar

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.iamnaran.designsystem.theme.AppIcons

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    toolbarTitle: String,
    barScrollBehavior: TopAppBarScrollBehavior,
    onActionCameraClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        scrollBehavior = barScrollBehavior,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(toolbarTitle, style = MaterialTheme.typography.titleMedium)
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                onActionCameraClick()
            }) {
                Icon(
                    imageVector = AppIcons.Camera,
                    contentDescription = "Camera"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                onProfileClick()
            }) {
                Icon(
                    imageVector = AppIcons.Person,
                    contentDescription = "Profile",
                )
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.onSecondary),
    )
}