package ru.yotfr.alarm.ui.alarmring

import AlarmTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.domain.model.AlarmModel
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun AlarmRingScreenPreview() {
    AlarmTheme {
        AlarmRingScreen(
            alarmModel = AlarmModel(
                label = "GHEw WEgbweb w ew we ew vWE bwe bweb WE bw e BWeb wenbwe hbwe hwe"
            )
        )
    }
}

@Composable
fun AlarmRingScreen(
    alarmModel: AlarmModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AlarmTheme.colors.background)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        TimeSection(
            modifier = Modifier.weight(3f),
            time = alarmModel.triggerTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        )
        Spacer(modifier = Modifier.height(16.dp))
        LabelSection(
            modifier = Modifier.weight(3f),
            label = alarmModel.label
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonSection(
            modifier = Modifier.weight(2f),
            onSnoozeClicked = {  },
            onDismissClicked = {  },
            isSnoozeActive = false
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}