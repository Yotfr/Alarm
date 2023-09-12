package ru.yotfr.alarm.ui.createalarm.screen

import AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.domain.model.WeekDays
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.punchedShadow
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Preview
@Composable
fun WeekDayRowPreview() {
    AlarmTheme {
        val activeList = remember { mutableStateListOf(WeekDays.MONDAY, WeekDays.SATURDAY) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AlarmTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            WeekDayRow(
                activeWeekDays = activeList,
                onWeekDayClicked = {
                    if (activeList.contains(it)) {
                        activeList.remove(it)
                    } else {
                        activeList.add(it)
                    }
                }
            )
        }
    }
}

@Composable
fun WeekDayRow(
    modifier: Modifier = Modifier,
    activeWeekDays: List<WeekDays>,
    onWeekDayClicked: (WeekDays) -> Unit
) {
    val spacing = 8.dp
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.MONDAY),
            weekDay = WeekDays.MONDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.TUESDAY),
            weekDay = WeekDays.TUESDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.WEDNESDAY),
            weekDay = WeekDays.WEDNESDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.THURSDAY),
            weekDay = WeekDays.THURSDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.FRIDAY),
            weekDay = WeekDays.FRIDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.SATURDAY),
            weekDay = WeekDays.SATURDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.SUNDAY),
            weekDay = WeekDays.SUNDAY,
            onClick = onWeekDayClicked
        )
    }
}

@Composable
fun WeekDayCard(
    active: Boolean,
    weekDay: WeekDays,
    onClick: (WeekDays) -> Unit
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick(weekDay) }
            .punchedShadow(
                offsetX = AlarmTheme.shadowOffset.default,
                offsetY = AlarmTheme.shadowOffset.default,
                blurRadius = AlarmTheme.shadowBlurRadius.default,
                shape = Shape.RoundedRect,
                darkColor = AlarmTheme.colors.darkShadow,
                lightColor = AlarmTheme.colors.lightShadow,
                cornerRadius = AlarmTheme.shapeCornerRadius.extraSmall
            )
            .clip(AlarmTheme.shape.extraSmall)
            .background(
                color = AlarmTheme.colors.background
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = when (weekDay) {
                WeekDays.MONDAY -> DayOfWeek.MONDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                WeekDays.TUESDAY -> DayOfWeek.TUESDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                WeekDays.WEDNESDAY -> DayOfWeek.WEDNESDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                WeekDays.THURSDAY -> DayOfWeek.THURSDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                WeekDays.FRIDAY -> DayOfWeek.FRIDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                WeekDays.SATURDAY -> DayOfWeek.SATURDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                WeekDays.SUNDAY -> DayOfWeek.SUNDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
            },
            style = AlarmTheme.typography.headline,
            color = if (!active) AlarmTheme.colors.disabledText else AlarmTheme.colors.text
        )
    }
}

