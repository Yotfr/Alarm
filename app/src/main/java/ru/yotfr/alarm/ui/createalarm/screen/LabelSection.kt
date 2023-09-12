package ru.yotfr.alarm.ui.createalarm.screen

import AlarmTheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.yotfr.alarm.R
import ru.yotfr.alarm.ui.common.Shape
import ru.yotfr.alarm.ui.common.pressedShadow
import ru.yotfr.alarm.ui.common.punchedShadow

@Preview
@Composable
fun PressedTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    AlarmTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AlarmTheme.colors.background)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            LabelSection(
                value = text,
                onValueChange = {
                    text = it
                }
            )
        }
    }
}

@Composable
fun LabelSection(
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .pressedShadow(
                offsetX = AlarmTheme.shadowOffset.default,
                offsetY = AlarmTheme.shadowOffset.default,
                blurRadius = AlarmTheme.shadowBlurRadius.default,
                darkColor = AlarmTheme.colors.darkShadow,
                lightColor = AlarmTheme.colors.lightShadow,
                shape = Shape.RoundedRect,
                cornerRadius = AlarmTheme.shapeCornerRadius.default,
                backgroundColor = AlarmTheme.colors.background
            )
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.label),
                style = AlarmTheme.typography.headline,
                color = AlarmTheme.colors.text
            )
            AnimatedVisibility(
                visible = value.isNotBlank(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onValueChange("") },
                    tint = AlarmTheme.colors.text
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        PressedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            }
        )
        Spacer(modifier = Modifier.height(22.dp))
    }
}


@Composable
fun PressedTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = AlarmTheme.typography.caption.copy(
            color = AlarmTheme.colors.text
        ),
        cursorBrush = Brush.verticalGradient(
            colors = listOf(
                AlarmTheme.colors.disabledText,
                AlarmTheme.colors.text,
                AlarmTheme.colors.disabledText
            )
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                    .padding(16.dp)
                    .animateContentSize()
            ) {
                if (value.isBlank()) {
                    Text(
                        text = stringResource(id = R.string.label_placeholder),
                        style = AlarmTheme.typography.caption,
                        color = AlarmTheme.colors.disabledText
                    )
                }
                innerTextField()
            }
        }
    )
}