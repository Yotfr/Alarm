package ru.yotfr.alarm.ui.alarmring

import AlarmTheme
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.data.service.AlarmService
import ru.yotfr.alarm.domain.model.AlarmModel
import java.time.format.DateTimeFormatter

@Composable
fun AlarmRingScreen(
    alarmModel: AlarmModel
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AlarmTheme.colors.background)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = alarmModel.triggerTime.format(DateTimeFormatter.ofPattern("HH:mm")),
            style = AlarmTheme.typography.displayLarge,
            color = AlarmTheme.colors.text
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = alarmModel.triggerTime.format(DateTimeFormatter.ofPattern("HH:mm")),
            style = AlarmTheme.typography.headline,
            color = AlarmTheme.colors.text
        )
    }

    Row (
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = {
            val intent = Intent(context, AlarmService::class.java)
            context.stopService(intent)
        }) {
            Text(text = "CANCEL")
        }
    }
}