package ru.yotfr.alarm.ui.alarmlist.screen

import AlarmTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import ru.yotfr.alarm.ui.theme.Geologica
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ActiveWeekDaysText(
    activeDays: List<DayOfWeek>,
    triggerTime: LocalDateTime,
    isActive: Boolean
) {
    if (activeDays.isEmpty()) {
        Text(
            text = triggerTime.format(DateTimeFormatter.ofPattern("EEE, dd MMM")),
            color = if (isActive) AlarmTheme.colors.text
            else AlarmTheme.colors.disabledText,
            style = AlarmTheme.typography.caption
        )
    } else {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = if (activeDays.contains(DayOfWeek.MONDAY)) {
                            if (isActive) {
                                AlarmTheme.colors.accent
                            } else {
                                AlarmTheme.colors.disabledAccent
                            }
                        } else {
                            if (isActive) {
                                AlarmTheme.colors.text
                            } else {
                                AlarmTheme.colors.disabledText
                            }
                        },
                        fontSize = 12.sp,
                        fontFamily = Geologica
                    )
                ) {
                    append(
                        DayOfWeek.MONDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                    )
                }
                withStyle(
                    style = SpanStyle(
                        color = if (activeDays.contains(DayOfWeek.TUESDAY)) {
                            if (isActive) {
                                AlarmTheme.colors.accent
                            } else {
                                AlarmTheme.colors.disabledAccent
                            }
                        } else {
                            if (isActive) {
                                AlarmTheme.colors.text
                            } else {
                                AlarmTheme.colors.disabledText
                            }
                        },
                        fontSize = 12.sp,
                        fontFamily = Geologica
                    )
                ) {
                    append(
                        DayOfWeek.TUESDAY.getDisplayName(TextStyle.FULL, Locale.getDefault())
                            .take(1)
                    )
                }
                withStyle(
                    style = SpanStyle(
                        color = if (activeDays.contains(DayOfWeek.WEDNESDAY)) {
                            if (isActive) {
                                AlarmTheme.colors.accent
                            } else {
                                AlarmTheme.colors.disabledAccent
                            }
                        } else {
                            if (isActive) {
                                AlarmTheme.colors.text
                            } else {
                                AlarmTheme.colors.disabledText
                            }
                        },
                        fontSize = 12.sp,
                        fontFamily = Geologica
                    )
                ) {
                    append(
                        DayOfWeek.WEDNESDAY.getDisplayName(TextStyle.FULL, Locale.getDefault())
                            .take(1)
                    )
                }
                withStyle(
                    style = SpanStyle(
                        color = if (activeDays.contains(DayOfWeek.THURSDAY)) {
                            if (isActive) {
                                AlarmTheme.colors.accent
                            } else {
                                AlarmTheme.colors.disabledAccent
                            }
                        } else {
                            if (isActive) {
                                AlarmTheme.colors.text
                            } else {
                                AlarmTheme.colors.disabledText
                            }
                        },
                        fontSize = 12.sp,
                        fontFamily = Geologica
                    )
                ) {
                    append(
                        DayOfWeek.THURSDAY.getDisplayName(TextStyle.FULL, Locale.getDefault())
                            .take(1)
                    )
                }
                withStyle(
                    style = SpanStyle(
                        color = if (activeDays.contains(DayOfWeek.FRIDAY)) {
                            if (isActive) {
                                AlarmTheme.colors.accent
                            } else {
                                AlarmTheme.colors.disabledAccent
                            }
                        } else {
                            if (isActive) {
                                AlarmTheme.colors.text
                            } else {
                                AlarmTheme.colors.disabledText
                            }
                        },
                        fontSize = 12.sp,
                        fontFamily = Geologica
                    )
                ) {
                    append(
                        DayOfWeek.FRIDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                    )
                }
                withStyle(
                    style = SpanStyle(
                        color = if (activeDays.contains(DayOfWeek.SATURDAY)) {
                            if (isActive) {
                                AlarmTheme.colors.accent
                            } else {
                                AlarmTheme.colors.disabledAccent
                            }
                        } else {
                            if (isActive) {
                                AlarmTheme.colors.text
                            } else {
                                AlarmTheme.colors.disabledText
                            }
                        },
                        fontSize = 12.sp,
                        fontFamily = Geologica
                    )
                ) {
                    append(
                        DayOfWeek.SATURDAY.getDisplayName(TextStyle.FULL, Locale.getDefault())
                            .take(1)
                    )
                }
                withStyle(
                    style = SpanStyle(
                        color = if (activeDays.contains(DayOfWeek.SUNDAY)) {
                            if (isActive) {
                                AlarmTheme.colors.accent
                            } else {
                                AlarmTheme.colors.disabledAccent
                            }
                        } else {
                            if (isActive) {
                                AlarmTheme.colors.text
                            } else {
                                AlarmTheme.colors.disabledText
                            }
                        },
                        fontSize = 12.sp,
                        fontFamily = Geologica
                    )
                ) {
                    append(
                        DayOfWeek.SUNDAY.getDisplayName(TextStyle.FULL, Locale.getDefault()).take(1)
                    )
                }
            }
        )
    }
}