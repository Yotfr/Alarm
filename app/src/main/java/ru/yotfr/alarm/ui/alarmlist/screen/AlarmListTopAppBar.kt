package ru.yotfr.alarm.ui.alarmlist.screen

import AlarmTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmListTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onEditClicked: () -> Unit,
    isInEditMode: Boolean
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.alarms),
                style = AlarmTheme.typography.headline,
                color = AlarmTheme.colors.text
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = AlarmTheme.colors.background
        ),
        scrollBehavior = scrollBehavior,
        actions = {
            Text(
                text = stringResource(id = if (isInEditMode) R.string.done else R.string.delete),
                style = AlarmTheme.typography.headline,
                color = AlarmTheme.colors.text,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { onEditClicked() }
            )
        }
    )
}