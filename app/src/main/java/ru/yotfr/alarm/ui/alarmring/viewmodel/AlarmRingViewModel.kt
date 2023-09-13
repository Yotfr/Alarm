package ru.yotfr.alarm.ui.alarmring.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.usecase.DismissAlarmUseCase
import ru.yotfr.alarm.domain.usecase.GetAlarmByIdUseCase
import ru.yotfr.alarm.ui.alarmring.event.AlarmRingEvent
import javax.inject.Inject

@HiltViewModel
class AlarmRingViewModel @Inject constructor(
    private val getAlarmByIdUseCase: GetAlarmByIdUseCase,
    private val dismissAlarmUseCase: DismissAlarmUseCase
) : ViewModel() {

    private val _alarm = MutableStateFlow(AlarmModel())
    val alarm = _alarm.asStateFlow()

    fun onEvent(event: AlarmRingEvent) {
        when (event) {
            is AlarmRingEvent.EnterScreen -> {
                enterScreen(event.id)
            }
            AlarmRingEvent.DismissAlarm -> {
                dismissAlarm()
            }
            AlarmRingEvent.SnoozeAlarm -> {

            }
        }
    }

    private fun dismissAlarm() {
        viewModelScope.launch {
            dismissAlarmUseCase(_alarm.value)
        }
    }

    private fun enterScreen(id: Long) {
        viewModelScope.launch {
            _alarm.value = getAlarmByIdUseCase(id)
        }
    }

}