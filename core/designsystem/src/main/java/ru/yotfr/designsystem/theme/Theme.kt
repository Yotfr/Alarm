package ru.yotfr.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


object AlarmTheme {
    val colors: AlarmColors
        @Composable
        get() = LocalColors.current
    val shape: Shape
        @Composable
        get() = LocalShapes.current
    val spacing: Spacing
        @Composable
        get() = LocalSpacing.current
    val shadowOffset: ShadowOffset
        @Composable
        get() = LocalShadowOffset.current
    val typography: AlarmTypography
        @Composable
        get() = LocalTypography.current
    val shadowBlurRadius: ShadowBlurRadius
        @Composable
        get() = LocalShadowBlurRadius.current
    val shapeCornerRadius: ShapeCornerRadius
        @Composable
        get() = LocalShapeCornerRadius.current
}

@Composable
fun AlarmTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (isDarkTheme) {
        AlarmColors(
            background = darkBackgroundColor,
            text = darkTextColor,
            accent = darkAccentColor,
            lightShadow = darkLightShadowColor,
            darkShadow = darkDarkShadowColor,
            disabledText = darkDisabledTextColor,
            disabledAccent = darkDisabledAccentColor,
            disabledBackground = darkDisabledBackgroundColor
        )
    } else {
        AlarmColors(
            background = lightBackgroundColor,
            text = lightTextColor,
            accent = lightAccentColor,
            lightShadow = lightLightShadowColor,
            darkShadow = lightDarkShadowColor,
            disabledText = lightDisabledTextColor,
            disabledAccent = lightDisabledAccentColor,
            disabledBackground = lightDisabledBackgroundColor
        )
    }

    val shape = Shape()
    val spacing = Spacing()
    val shadowOffset = ShadowOffset()
    val typography = AlarmTypography()
    val blurRadius = ShadowBlurRadius()
    val shapeCornerRadius = ShapeCornerRadius()
    CompositionLocalProvider(
        LocalShadowOffset provides shadowOffset,
        LocalSpacing provides spacing,
        LocalShapes provides shape,
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalShadowBlurRadius provides blurRadius,
        LocalShapeCornerRadius provides shapeCornerRadius
    ) {
        MaterialTheme(
            content = content
        )
    }
}