package ru.yotfr.alarm.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ru.yotfr.alarm.data.AlarmService

@Composable
fun AlarmRingScreen() {
    val context = LocalContext.current

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