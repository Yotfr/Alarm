package ru.yotfr.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import ru.yotfr.designsystem.theme.AlarmTheme

enum class BoxSize {
    SMALL, DEFAULT, LARGE
}


/**
 * @param[modifier] must define the size of a box
 */
@Composable
fun PunchedBox(
    modifier: Modifier,
    clickable: Boolean = false,
    onClick: () -> Unit = {},
    size: BoxSize = BoxSize.DEFAULT,
    contentAlignment: Alignment = Alignment.Center,
    backgroundColor: Color = AlarmTheme.colors.accent,
    content: @Composable() (BoxScope.() -> Unit)
) {
    val offset = when(size) {
        BoxSize.SMALL -> AlarmTheme.shadowOffset.small
        BoxSize.DEFAULT -> AlarmTheme.shadowOffset.default
        BoxSize.LARGE -> AlarmTheme.shadowOffset.large
    }
    val blurRadius = when(size) {
        BoxSize.SMALL -> AlarmTheme.shadowBlurRadius.default
        BoxSize.DEFAULT -> AlarmTheme.shadowBlurRadius.large
        BoxSize.LARGE -> AlarmTheme.shadowBlurRadius.large
    }
    val cornerRadius = when(size) {
        BoxSize.SMALL -> AlarmTheme.shapeCornerRadius.small
        BoxSize.DEFAULT -> AlarmTheme.shapeCornerRadius.default
        BoxSize.LARGE -> AlarmTheme.shapeCornerRadius.large
    }
    val shape = when(size) {
        BoxSize.SMALL -> AlarmTheme.shape.small
        BoxSize.DEFAULT -> AlarmTheme.shape.default
        BoxSize.LARGE -> AlarmTheme.shape.large
    }
    Box(
        modifier = modifier
            .clickable(
                enabled = clickable,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick() }
            .punchedShadow(
                offsetX = offset,
                offsetY = offset,
                blurRadius = blurRadius,
                shape = Shape.RoundedRect,
                darkColor = AlarmTheme.colors.darkShadow,
                lightColor = AlarmTheme.colors.lightShadow,
                cornerRadius = cornerRadius
            )
            .clip(shape)
            .background(
                color = backgroundColor
            ),
        contentAlignment = contentAlignment
    ) {
        content()
    }
}