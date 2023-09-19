package ru.yotfr.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.designsystem.icon.AlarmIcons
import ru.yotfr.designsystem.theme.AlarmTheme

@Preview
@Composable
fun AlarmFabPreview() {
    AlarmTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AlarmTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            AlarmFAB(
                onClick = {},
                isVisible = true,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

/**
 * @param[modifier] must define the size of a box
 */
@Composable
fun AlarmFAB(
    modifier: Modifier,
    onClick: () -> Unit,
    isVisible: Boolean
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it * 2 }),
        exit = slideOutVertically(targetOffsetY = { it * 2 })
    ) {
        PunchedBox(
            modifier = modifier,
            clickable = true,
            onClick = onClick,
            size = BoxSize.LARGE
        ) {
            Icon(
                imageVector = AlarmIcons.add,
                contentDescription = null,
                tint = AlarmTheme.colors.background
            )
        }
    }
}
