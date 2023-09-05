package ru.yotfr.alarm.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AlarmItemPreview() {
    AlarmItem()
}

@Preview
@Composable
fun AlarmsListScreenPreview() {
    AlarmsListScreen()
}

@Composable
fun AlarmsListScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            AlarmItem()
        }
        item {
            AlarmItem()
        }
    }
}

@Composable
fun AlarmItem() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "23:55"
        )
        Switch(
            checked = false,
            onCheckedChange = {}
        )
    }
}