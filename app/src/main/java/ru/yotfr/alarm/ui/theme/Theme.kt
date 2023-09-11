import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.yotfr.alarm.ui.theme.LocalShadowOffset
import ru.yotfr.alarm.ui.theme.LocalShapes
import ru.yotfr.alarm.ui.theme.LocalSpacing
import ru.yotfr.alarm.ui.theme.ShadowOffset
import ru.yotfr.alarm.ui.theme.Shape
import ru.yotfr.alarm.ui.theme.Spacing


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
}

@Composable
fun AlarmTheme(
    content: @Composable () -> Unit
) {
    val colors = AlarmColors(
        background = backgroundColor,
        text = textColor,
        accent = accentColor,
        lightShadow = lightShadowColor,
        darkShadow = darkShadowColor
    )
    val shape = Shape()
    val spacing = Spacing()
    val shadowOffset = ShadowOffset()
    CompositionLocalProvider(
        LocalShadowOffset provides shadowOffset,
        LocalSpacing provides spacing,
        LocalShapes provides shape,
        LocalColors provides colors
    ) {
        MaterialTheme(
            content = content
        )
    }
}