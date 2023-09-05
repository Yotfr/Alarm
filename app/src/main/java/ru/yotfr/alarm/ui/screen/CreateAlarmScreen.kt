package ru.yotfr.alarm.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.custom.TimePicker


@Preview
@Composable
fun CreateAlarmScreenPreview() {
    CreateAlarmScreen()
}

@Composable
fun CreateAlarmScreen() {

    val currentHour = remember { mutableStateOf(0) }
    val currentMinute = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TimePicker(
                state = currentHour,
                range = 0..23,
                onStateChanged = { currentHour.value = it }
            )
            Spacer(modifier = Modifier.width(10.dp))
            TimePicker(
                state = currentMinute,
                range = 0..59,
                onStateChanged = { currentMinute.value = it }
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        TextButton(onClick = {

        }) {
            Text(text = "Create")
        }
    }



}