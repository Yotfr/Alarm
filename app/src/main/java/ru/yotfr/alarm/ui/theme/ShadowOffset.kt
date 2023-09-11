package ru.yotfr.alarm.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ShadowOffset(
    val small: Dp = 1.dp,
    val default: Dp = 3.dp,
    val large: Dp = 4.dp
)

val LocalShadowOffset = compositionLocalOf { ShadowOffset() }

val MaterialTheme.shadowOffset: ShadowOffset
    @Composable
    @ReadOnlyComposable
    get() = LocalShadowOffset.current