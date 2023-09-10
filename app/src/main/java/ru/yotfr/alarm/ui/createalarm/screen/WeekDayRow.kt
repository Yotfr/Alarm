package ru.yotfr.alarm.ui.createalarm.screen

import AlarmTheme
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.yotfr.alarm.domain.model.WeekDays

@Preview
@Composable
fun WeekDayRowPreview() {
    AlarmTheme {
        val activeList = remember { mutableStateListOf(WeekDays.MONDAY, WeekDays.SATURDAY) }
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

@Composable
fun WeekDayRow(
    activeWeekDays: List<WeekDays>,
    onWeekDayClicked: (WeekDays) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.MONDAY),
            weekDay = WeekDays.MONDAY,
            onClick = onWeekDayClicked
        )
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.TUESDAY),
            weekDay = WeekDays.TUESDAY,
            onClick = onWeekDayClicked
        )
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.WEDNESDAY),
            weekDay = WeekDays.WEDNESDAY,
            onClick = onWeekDayClicked
        )
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.THURSDAY),
            weekDay = WeekDays.THURSDAY,
            onClick = onWeekDayClicked
        )
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.FRIDAY),
            weekDay = WeekDays.FRIDAY,
            onClick = onWeekDayClicked
        )
        WeekDayCard(
            active = activeWeekDays.contains(WeekDays.SATURDAY),
            weekDay = WeekDays.SATURDAY,
            onClick = onWeekDayClicked
        )
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
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = AlarmTheme.colors.surface,
        modifier = Modifier
            .border(
                brush = Brush.linearGradient(
                    colors = listOf(
                        AlarmTheme.colors.accentBorder,
                        AlarmTheme.colors.shadow
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                ),
                width = 1.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .shadow(
                elevation = 16.dp,
                clip = true,
                spotColor = AlarmTheme.colors.shadow,
                shape = RoundedCornerShape(22.dp)
            )
            .width(32.dp)
            .padding(8.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick(weekDay)
            }
    ) {
        Text(
            text = when (weekDay) {
                WeekDays.MONDAY -> "M"
                WeekDays.TUESDAY -> "T"
                WeekDays.WEDNESDAY -> "W"
                WeekDays.THURSDAY -> "T"
                WeekDays.FRIDAY -> "F"
                WeekDays.SATURDAY -> "S"
                WeekDays.SUNDAY -> "S"
            },
            fontSize = 18.sp,
            color = if (!active) AlarmTheme.colors.text.copy(alpha = 0.25f) else AlarmTheme.colors.text,
            textAlign = TextAlign.Center
        )
    }
}

