package com.iamnaran.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val oneDim: Dp = 1.dp,
    val zeroDim: Dp = 0.dp,

    val extraSmall: Dp = 2.dp,
    val default: Dp = 4.dp,
    val normal: Dp = 8.dp,
    val medium: Dp = 10.dp,
    val large: Dp = 16.dp,
    val extraLarge: Dp = 32.dp,

    val paddingSmall: Dp = 2.dp,
    val paddingDefault: Dp = 4.dp,
    val paddingNormal: Dp = 8.dp,
    val paddingMedium: Dp = 10.dp,
    val paddingLarge: Dp = 16.dp,
    val paddingExtraLarge: Dp = 32.dp,


    val sizeSmall: Dp = 2.dp,
    val sizeDefault: Dp = 4.dp,
    val sizeNormal: Dp = 8.dp,
    val sizeMedium: Dp = 10.dp,
    val sizeLarge: Dp = 16.dp,
    val sizeExtraLarge: Dp = 32.dp,
    val sizeButton: Dp = 50.dp,
    val sizeIcon: Dp = 50.dp,

    )

val LocalDimens = compositionLocalOf { Dimens() }

val MaterialTheme.dimens: Dimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current