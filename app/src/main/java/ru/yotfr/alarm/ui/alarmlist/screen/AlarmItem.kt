package ru.yotfr.alarm.ui.alarmlist.screen

import AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.yotfr.alarm.R
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.model.WeekDays
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
//
//@Preview
//@Composable
//fun AlarmItemPreview() {
//
//    var activeFirst by remember { mutableStateOf(false) }
//    var activeSecond by remember { mutableStateOf(false) }
//
//    AlarmTheme {
//        Column(
//            modifier = Modifier
//                .background(color = AlarmTheme.colors.background)
//                .fillMaxSize()
//                .padding(32.dp)
//        ) {
//            AlarmItem(
//                alarmModel = AlarmModel(
//                    id = 0L,
//                    triggerTime = LocalDateTime.now(),
//                    isActive = activeFirst,
//                    activeDays = emptyList()
//                ),
//                switchCheckedChange = { activeFirst = it },
//                {},{}
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            AlarmItem(
//                alarmModel = AlarmModel(
//                    id = 0L,
//                    triggerTime = LocalDateTime.now(),
//                    isActive = activeSecond,
//                    activeDays = listOf(WeekDays.SATURDAY, WeekDays.TUESDAY)
//                ),
//                switchCheckedChange = { activeSecond = it },
//                {},{}
//            )
//        }
//    }
//}
//
//@Composable
//fun AlarmItem(
//    alarmModel: AlarmModel,
//    switchCheckedChange: (Boolean) -> Unit,
//    onClick: (AlarmModel) -> Unit,
//    onDeleteClicked: (AlarmModel) -> Unit
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//    Box {
//        Surface(
//            shape = RoundedCornerShape(22.dp),
//            color = AlarmTheme.colors.surface,
//            modifier = Modifier
//                .border(
//                    brush = Brush.linearGradient(
//                        colors = listOf(
//                            AlarmTheme.colors.accentBorder,
//                            AlarmTheme.colors.shadow
//                        ),
//                        start = Offset(0f, 0f),
//                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
//                    ),
//                    width = 1.dp,
//                    shape = RoundedCornerShape(22.dp)
//                )
//                .shadow(
//                    elevation = 16.dp,
//                    clip = true,
//                    spotColor = AlarmTheme.colors.shadow,
//                    shape = RoundedCornerShape(22.dp)
//                )
//                .clickable(
//                    indication = null,
//                    interactionSource = remember { MutableInteractionSource() }
//                ) { onClick(alarmModel) }
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 18.dp)
//            ) {
//                Text(
//                    text = alarmModel.triggerTime.format(DateTimeFormatter.ofPattern("HH:mm")),
//                    fontSize = 34.sp,
//                    color = if (alarmModel.isActive) AlarmTheme.colors.text else AlarmTheme.colors.text.copy(
//                        alpha = 0.25f
//                    )
//                )
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.End
//                ) {
//                    ActiveWeekDaysText(
//                        activeDays = alarmModel.activeDays,
//                        triggerTime = alarmModel.triggerTime,
//                        isActive = alarmModel.isActive
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    AlarmSwitch(
//                        active = alarmModel.isActive,
//                        onCheckedChange = switchCheckedChange
//                    )
//                }
//            }
//        }
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            DropdownMenuItem(
//                text = {
//                    Text(
//                        text = stringResource(id = R.string.alarms),
//                        fontSize = 18.sp,
//                        color = AlarmTheme.colors.text
//                    )
//                },
//                onClick = {
//                    expanded = false
//                    onDeleteClicked(alarmModel)
//                },
//                leadingIcon = {
//                    Icon(
//                        Icons.Outlined.Delete,
//                        contentDescription = null
//                    )
//                })
//        }
//    }
//
//}
//
//@Composable
//fun ActiveWeekDaysText(activeDays: List<WeekDays>, triggerTime: LocalDateTime, isActive: Boolean) {
//    if (activeDays.isEmpty()) {
//        Text(
//            text = triggerTime.format(DateTimeFormatter.ofPattern("EEE, dd MMM")),
//            color = AlarmTheme.colors.text,
//            fontSize = 12.sp
//        )
//    } else {
//        Text(
//            buildAnnotatedString {
//                withStyle(
//                    style = SpanStyle(
//                        color = if (activeDays.contains(WeekDays.MONDAY)) {
//                            if (isActive) {
//                                AlarmTheme.colors.primary
//                            } else {
//                                AlarmTheme.colors.primary.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        } else {
//                            if (isActive) {
//                                AlarmTheme.colors.text
//                            } else {
//                                AlarmTheme.colors.text.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        },
//                        fontSize = 12.sp
//                    )
//                ) {
//                    append("M")
//                }
//                withStyle(
//                    style = SpanStyle(
//                        color = if (activeDays.contains(WeekDays.TUESDAY)) {
//                            if (isActive) {
//                                AlarmTheme.colors.primary
//                            } else {
//                                AlarmTheme.colors.primary.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        } else {
//                            if (isActive) {
//                                AlarmTheme.colors.text
//                            } else {
//                                AlarmTheme.colors.text.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        },
//                        fontSize = 12.sp
//                    )
//                ) {
//                    append("T")
//                }
//                withStyle(
//                    style = SpanStyle(
//                        color = if (activeDays.contains(WeekDays.WEDNESDAY)) {
//                            if (isActive) {
//                                AlarmTheme.colors.primary
//                            } else {
//                                AlarmTheme.colors.primary.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        } else {
//                            if (isActive) {
//                                AlarmTheme.colors.text
//                            } else {
//                                AlarmTheme.colors.text.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        },
//                        fontSize = 12.sp
//                    )
//                ) {
//                    append("W")
//                }
//                withStyle(
//                    style = SpanStyle(
//                        color = if (activeDays.contains(WeekDays.THURSDAY)) {
//                            if (isActive) {
//                                AlarmTheme.colors.primary
//                            } else {
//                                AlarmTheme.colors.primary.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        } else {
//                            if (isActive) {
//                                AlarmTheme.colors.text
//                            } else {
//                                AlarmTheme.colors.text.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        },
//                        fontSize = 12.sp
//                    )
//                ) {
//                    append("T")
//                }
//                withStyle(
//                    style = SpanStyle(
//                        color = if (activeDays.contains(WeekDays.FRIDAY)) {
//                            if (isActive) {
//                                AlarmTheme.colors.primary
//                            } else {
//                                AlarmTheme.colors.primary.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        } else {
//                            if (isActive) {
//                                AlarmTheme.colors.text
//                            } else {
//                                AlarmTheme.colors.text.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        },
//                        fontSize = 12.sp
//                    )
//                ) {
//                    append("F")
//                }
//                withStyle(
//                    style = SpanStyle(
//                        color = if (activeDays.contains(WeekDays.SATURDAY)) {
//                            if (isActive) {
//                                AlarmTheme.colors.primary
//                            } else {
//                                AlarmTheme.colors.primary.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        } else {
//                            if (isActive) {
//                                AlarmTheme.colors.text
//                            } else {
//                                AlarmTheme.colors.text.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        },
//                        fontSize = 12.sp
//                    )
//                ) {
//                    append("S")
//                }
//                withStyle(
//                    style = SpanStyle(
//                        color = if (activeDays.contains(WeekDays.SUNDAY)) {
//                            if (isActive) {
//                                AlarmTheme.colors.primary
//                            } else {
//                                AlarmTheme.colors.primary.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        } else {
//                            if (isActive) {
//                                AlarmTheme.colors.text
//                            } else {
//                                AlarmTheme.colors.text.copy(
//                                    alpha = 0.25f
//                                )
//                            }
//                        },
//                        fontSize = 12.sp
//                    )
//                ) {
//                    append("S")
//                }
//            }
//        )
//    }
//}