package ru.yotfr.alarm.ui.alarmlist.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.pressedShadow
import ru.yotfr.alarm.ui.common.punchedShadow


val backgroundColor = Color(0xFFEDEDED)
val textColor = Color(0xFF828E94)
val accentColor = Color(0xFFFF6B00)
val cornerRadius = 12.dp

@Preview
@Composable
fun TestItemPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        TestItem()
    }
}

@Composable
fun TestItem() {

    var isChec by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .punchedShadow(
                offsetX = 3.dp,
                offsetY = 3.dp,
                blurRadius = 4.dp,
                darkColor = Color.Black.copy(alpha = 0.25f),
                lightColor = Color.White,
                shape = Shape.RoundedRect,
                cornerRadius = cornerRadius
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "16:44",
            fontSize = 34.sp,
            color = textColor
        )
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
//            ActiveWeekDaysText(
//                activeDays = listOf(WeekDays.SUNDAY, WeekDays.THURSDAY),
//                triggerTime = LocalDateTime.now(),
//                isActive = true
//            )
            Spacer(modifier = Modifier.width(16.dp))
            CustomSwitch(
                isChecked = isChec,
                onCheckedChange = { isChec = it }
            )
        }
    }
}

@Composable
fun CustomSwitch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Box(
        modifier = modifier
            .height(24.dp)
            .width(48.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onCheckedChange(!isChecked)
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .pressedShadow(
                    offsetX = 3.dp,
                    offsetY = 3.dp,
                    blurRadius = 4.dp,
                    darkColor = Color.Black.copy(alpha = 0.25f),
                    lightColor = Color.White,
                    shape = Shape.RoundedRect,
                    cornerRadius = cornerRadius,
                    backgroundColor = if (isChecked) accentColor else backgroundColor
                )
        )
        Box(
            modifier = Modifier
                .size(24.dp)
                .align(
                    if (isChecked) Alignment.CenterEnd else Alignment.CenterStart
                )
                .clip(CircleShape)
                .pressedShadow(
                    offsetX = 3.dp,
                    offsetY = 3.dp,
                    blurRadius = 4.dp,
                    shape = Shape.Circle,
                    darkColor = Color.Black.copy(alpha = 0.25f),
                    lightColor = Color.White,
                    backgroundColor = textColor
                )
//                .clip(CircleShape)
//                .background(
//                    textColor
//                )
        )
    }
}