package com.iamnaran.designsystem.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.iamnaran.designsystem.R

val appFonts = FontFamily(
    Font(R.font.light, FontWeight.Light),
    Font(R.font.extra_light, FontWeight.ExtraLight),
    Font(R.font.thin, FontWeight.Thin),
    Font(R.font.regular, FontWeight.Normal),
    Font(R.font.medium, FontWeight.Medium),
    Font(R.font.semi_bold, FontWeight.SemiBold),
    Font(R.font.bold, FontWeight.Bold),
    Font(R.font.extra_bold, FontWeight.Black),
    Font(R.font.italic, FontWeight.Normal, FontStyle.Italic),
)