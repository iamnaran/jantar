package com.iamnaran.navigation.bar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.iamnaran.designsystem.theme.AppIcons
import com.iamnaran.designsystem.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FancyAppBar(
    toolbarTitle: String,
    barScrollBehavior: TopAppBarScrollBehavior
) {

    TopAppBar(
        modifier = Modifier.padding(start = MaterialTheme.dimens.normal),
        scrollBehavior = barScrollBehavior,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(toolbarTitle, style = MaterialTheme.typography.titleMedium)
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        )
    )

}