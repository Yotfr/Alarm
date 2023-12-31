package ru.yotfr.designsystem.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Shape(
    val small: Shape = RoundedCornerShape(4.dp),
    val default: Shape = RoundedCornerShape(8.dp),
    val large: Shape = RoundedCornerShape(12.dp),
    val circle: Shape = CircleShape
)

val LocalShapes = staticCompositionLocalOf { Shape() }