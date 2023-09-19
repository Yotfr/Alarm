package ru.yotfr.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ShapeCornerRadius(
    val small: Dp = 4.dp,
    val default: Dp = 8.dp,
    val large: Dp = 12.dp
)

val LocalShapeCornerRadius = compositionLocalOf { ShapeCornerRadius() }