package ru.yotfr.alarm.ui.quest

import AlarmTheme
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
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.punchedShadow

@Preview
@Composable
fun QuestScreenPreview() {
    AlarmTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AlarmTheme.colors.background)
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
                offsetX = AlarmTheme.shadowOffset.default,
                offsetY = AlarmTheme.shadowOffset.default,
                blurRadius = AlarmTheme.shadowBlurRadius.default,
                shape = Shape.RoundedRect,
                darkColor = AlarmTheme.colors.darkShadow,
                lightColor = AlarmTheme.colors.lightShadow,
                cornerRadius = AlarmTheme.shapeCornerRadius.default
            )
            .clip(AlarmTheme.shape.default)
            .background(
                color = AlarmTheme.colors.background
            )
    )
}


