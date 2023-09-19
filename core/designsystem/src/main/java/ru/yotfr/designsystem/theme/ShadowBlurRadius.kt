package ru.yotfr.designsystem.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ShadowBlurRadius(
    val default: Dp = 4.dp,
    val large: Dp = 8.dp
)

val LocalShadowBlurRadius = compositionLocalOf { ShadowBlurRadius() }