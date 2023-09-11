package ru.yotfr.alarm.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class Shape(
    val default: Shape = RoundedCornerShape(12.dp)
)

val LocalShapes = staticCompositionLocalOf { Shape() }