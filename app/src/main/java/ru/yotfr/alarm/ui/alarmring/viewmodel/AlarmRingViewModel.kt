package ru.yotfr.alarm.ui.alarmring.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.yotfr.model.AlarmModel
import ru.yotfr.domain.DismissAlarmUseCase
import ru.yotfr.domain.GetAlarmByIdUseCase
import ru.yotfr.alarm.ui.alarmring.event.AlarmRingEvent
import ru.yotfr.alarm.ui.createalarm.event.CreateAlarmScreenEvent
import javax.inject.Inject

@HiltViewModel
class AlarmRingViewModel @Inject constructor(
    private val getAlarmByIdUseCase: ru.yotfr.domain.GetAlarmByIdUseCase,
    private val dismissAlarmUseCase: ru.yotfr.domain.DismissAlarmUseCase
) : ViewModel() {

    private val _alarm = MutableStateFlow(ru.yotfr.model.AlarmModel())
    val alarm = _alarm.asStateFlow()

    private val _event = Channel<CreateAlarmScreenEvent>()
    val event = _event.receiveAsFlow()

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
            _event.send(CreateAlarmScreenEvent.NavigateBack)
        }
    }

    private fun enterScreen(id: Long) {
        viewModelScope.launch {
            _alarm.value = getAlarmByIdUseCase(id)
        }
    }

}