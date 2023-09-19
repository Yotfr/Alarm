package ru.yotfr.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ShadowOffset(
    val small: Dp = 1.dp,
    val default: Dp = 4.dp,
    val large: Dp = 8.dp
)

val LocalShadowOffset = compositionLocalOf { ShadowOffset() }