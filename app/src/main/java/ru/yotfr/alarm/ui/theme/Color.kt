import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class AlarmColors(
    val background: Color,
    val text: Color,
    val disabledText: Color,
    val accent: Color,
    val disabledAccent: Color,
    val lightShadow: Color,
    val darkShadow: Color,
    val disabledBackground: Color
)

val LocalColors = staticCompositionLocalOf {
    AlarmColors(
        background = Color.Unspecified,
        text = Color.Unspecified,
        accent = Color.Unspecified,
        lightShadow = Color.Unspecified,
        darkShadow = Color.Unspecified,
        disabledText = Color.Unspecified,
        disabledAccent = Color.Unspecified,
        disabledBackground = Color.Unspecified
    )
}


val backgroundColor = Color(0xFFEDEDED)
val disabledBackgroundColor = Color(0x50EDEDED)
val textColor = Color(0xFF828E94)
val disabledTextColor = Color(0x50828E94)
val accentColor = Color(0xFFFF6B00)
val disabledAccentColor = Color(0x50FF6B00)
val lightShadowColor = Color(0xFFFFFFFF)
val darkShadowColor = Color(0x25000000)