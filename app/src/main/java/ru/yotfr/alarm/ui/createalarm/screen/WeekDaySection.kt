package ru.yotfr.alarm.ui.createalarm.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.R
import ru.yotfr.designsystem.component.Shape
import ru.yotfr.designsystem.component.punchedShadow
import java.time.DayOfWeek

@Composable
fun WeekDaySection(
    activeWeekDays: List<DayOfWeek>,
    onWeekDayClicked: (DayOfWeek) -> Unit,
    onClearWeekDaysClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .punchedShadow(
                offsetX = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                offsetY = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                blurRadius = ru.yotfr.designsystem.theme.AlarmTheme.shadowBlurRadius.default,
                shape = ru.yotfr.designsystem.component.Shape.RoundedRect,
                darkColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.darkShadow,
                lightColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.lightShadow,
                cornerRadius = ru.yotfr.designsystem.theme.AlarmTheme.shapeCornerRadius.default
            )
            .clip(ru.yotfr.designsystem.theme.AlarmTheme.shape.default)
            .background(
                color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background
            )
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.repeat),
                style = ru.yotfr.designsystem.theme.AlarmTheme.typography.headline,
                color = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
            )
            AnimatedVisibility(
                visible = activeWeekDays.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onClearWeekDaysClicked() },
                    tint = ru.yotfr.designsystem.theme.AlarmTheme.colors.text
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        WeekDayRow(
            activeWeekDays = activeWeekDays,
            onWeekDayClicked = onWeekDayClicked
        )
        Spacer(modifier = Modifier.height(22.dp))
    }
}