package ru.yotfr.alarm.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun OnPauseOnDestroyEffect(
    onPause: () -> Unit,
    onDestroy: () -> Unit,
) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val currentOnPause by rememberUpdatedState(onPause)
    val currentOnDestroy by rememberUpdatedState(onDestroy)

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    currentOnPause()
                }
                Lifecycle.Event.ON_DESTROY -> {
                    currentOnDestroy()
                }
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}