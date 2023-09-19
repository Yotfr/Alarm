package ru.yotfr.alarm

import ru.yotfr.designsystem.theme.AlarmTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.toArgb
import dagger.hilt.android.AndroidEntryPoint
import ru.yotfr.alarm.ui.navigation.AlarmNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ru.yotfr.designsystem.theme.AlarmTheme {
                val backgroundArgbColor =
                    ru.yotfr.designsystem.theme.AlarmTheme.colors.background.toArgb()
                enableEdgeToEdge(
                    statusBarStyle = if (isSystemInDarkTheme()) {
                        SystemBarStyle.dark(
                            backgroundArgbColor
                        )
                    } else {
                        SystemBarStyle.light(
                            backgroundArgbColor, backgroundArgbColor
                        )
                    }
                )
                AlarmNavHost()
            }
        }
    }
}