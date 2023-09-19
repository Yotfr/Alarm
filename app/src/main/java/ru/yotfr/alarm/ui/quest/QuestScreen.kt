package ru.yotfr.alarm.ui.quest

import ru.yotfr.designsystem.theme.AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.designsystem.component.Shape
import ru.yotfr.designsystem.component.punchedShadow

@Preview
@Composable
fun QuestScreenPreview() {
    ru.yotfr.designsystem.theme.AlarmTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ru.yotfr.designsystem.theme.AlarmTheme.colors.background)
        ) {
            QuestScreen()
        }
    }
}

@Composable
fun QuestScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Puzzle()
    }
}

@Composable
fun Puzzle() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        PuzzleGridRow()
        Spacer(modifier = Modifier.height(16.dp))
        PuzzleGridRow()
        Spacer(modifier = Modifier.height(16.dp))
        PuzzleGridRow()
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun PuzzleGridRow() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(32.dp))
        PuzzleGridCell(
            isActive = false,
            onClick = {},
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        PuzzleGridCell(
            isActive = true,
            onClick = {},
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        PuzzleGridCell(
            isActive = false,
            onClick = {},
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(32.dp))
    }
}

@Composable
fun PuzzleGridCell(
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
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
    )
}


