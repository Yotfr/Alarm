package ru.yotfr.alarm.ui.createalarm.screen

import ru.yotfr.designsystem.theme.AlarmTheme
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
import ru.yotfr.designsystem.component.Shape
import ru.yotfr.designsystem.component.pressedShadow
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Preview
@Composable
fun WeekDayRowPreview() {
    ru.yotfr.designsystem.theme.AlarmTheme {
        val activeList = remember { mutableStateListOf(DayOfWeek.MONDAY, DayOfWeek.SATURDAY) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background),
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
    activeWeekDays: List<DayOfWeek>,
    onWeekDayClicked: (DayOfWeek) -> Unit
) {
    val spacing = 8.dp
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        WeekDayCard(
            active = activeWeekDays.contains(DayOfWeek.MONDAY),
            weekDay = DayOfWeek.MONDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(DayOfWeek.TUESDAY),
            weekDay = DayOfWeek.TUESDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(DayOfWeek.WEDNESDAY),
            weekDay = DayOfWeek.WEDNESDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(DayOfWeek.THURSDAY),
            weekDay = DayOfWeek.THURSDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(DayOfWeek.FRIDAY),
            weekDay = DayOfWeek.FRIDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(DayOfWeek.SATURDAY),
            weekDay = DayOfWeek.SATURDAY,
            onClick = onWeekDayClicked
        )
        Spacer(modifier = Modifier.width(spacing))
        WeekDayCard(
            active = activeWeekDays.contains(DayOfWeek.SUNDAY),
            weekDay = DayOfWeek.SUNDAY,
            onClick = onWeekDayClicked
        )
    }
}

@Composable
fun WeekDayCard(
    active: Boolean,
    weekDay: DayOfWeek,
    onClick: (DayOfWeek) -> Unit
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onClick(weekDay) }
            .clip(ru.yotfr.designsystem.theme.AlarmTheme.shape.extraSmall)
            .pressedShadow(
                offsetX = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                offsetY = ru.yotfr.designsystem.theme.AlarmTheme.shadowOffset.default,
                blurRadius = ru.yotfr.designsystem.theme.AlarmTheme.shadowBlurRadius.default,
                darkColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.darkShadow,
                lightColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.lightShadow,
                shape = ru.yotfr.designsystem.component.Shape.RoundedRect,
                cornerRadius = ru.yotfr.designsystem.theme.AlarmTheme.shapeCornerRadius.extraSmall,
                backgroundColor = ru.yotfr.designsystem.theme.AlarmTheme.colors.background
            )
        ,contentAlignment = Alignment.Center
    ) {
        Text(
            text = when (weekDay) {
                DayOfWeek.MONDAY -> DayOfWeek.MONDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                DayOfWeek.TUESDAY -> DayOfWeek.TUESDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                DayOfWeek.WEDNESDAY -> DayOfWeek.WEDNESDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                DayOfWeek.THURSDAY -> DayOfWeek.THURSDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                DayOfWeek.FRIDAY -> DayOfWeek.FRIDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                DayOfWeek.SATURDAY -> DayOfWeek.SATURDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                DayOfWeek.SUNDAY -> DayOfWeek.SUNDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
            },
            style = ru.yotfr.designsystem.theme.AlarmTheme.typography.headline,
            color = if (!active) ru.yotfr.designsystem.theme.AlarmTheme.colors.disabledText else ru.yotfr.designsystem.theme.AlarmTheme.colors.text
        )
    }
}

