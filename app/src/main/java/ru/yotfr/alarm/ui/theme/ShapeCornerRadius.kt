package ru.yotfr.alarm.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ShapeCornerRadius(
    val default: Dp = 12.dp,
    val small: Dp = 8.dp
)

val LocalShapeCornerRadius = compositionLocalOf { ShapeCornerRadius() }