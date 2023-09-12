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


val lightBackgroundColor = Color(0xFFEDEDED)
val lightDisabledBackgroundColor = Color(0x50EDEDED)
val lightTextColor = Color(0xFF828E94)
val lightDisabledTextColor = Color(0x50828E94)
val lightAccentColor = Color(0xFFFF6B00)
val lightDisabledAccentColor = Color(0x50FF6B00)
val lightLightShadowColor = Color(0xFFFFFFFF)
val lightDarkShadowColor = Color(0x25000000)


val darkBackgroundColor = Color(0xFF828E94)
val darkDisabledBackgroundColor = Color(0x50828E94)
val darkTextColor = Color(0xFFEDEDED)
val darkDisabledTextColor = Color(0x50EDEDED)
val darkAccentColor = Color(0xFFFF6B00)
val darkDisabledAccentColor = Color(0x50FF6B00)
val darkLightShadowColor = Color(0xFFFFFFFF)
val darkDarkShadowColor = Color(0x25000000)