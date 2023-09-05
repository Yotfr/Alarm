package ru.yotfr.alarm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yotfr.alarm.domain.AlarmModel
import ru.yotfr.alarm.domain.CreateNewAlarmUseCase
import ru.yotfr.alarm.ui.state.CreateAlarmScreenState
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CreateAlarmViewModel @Inject constructor(
    private val createNewAlarmUseCase: CreateNewAlarmUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow(CreateAlarmScreenState())
    val screenState = _screenState.asStateFlow()

    fun createNewAlarm(
        hour: Int, minute: Int
    ) {
        val nowTime = LocalDateTime.now()
        val newAlarmTriggerTime = LocalDateTime.of(
            nowTime.year,
            nowTime.month,
            nowTime.dayOfMonth,
            hour,
            minute
        )
        val alarmTriggerTime = if (newAlarmTriggerTime.isBefore(nowTime)) {
            newAlarmTriggerTime.plusDays(1)
        } else newAlarmTriggerTime
        val newAlarmModel = AlarmModel(
            id = 0,
            triggerTime = alarmTriggerTime,
            isActive = true
        )
        viewModelScope.launch {
            createNewAlarmUseCase(newAlarmModel)
        }
    }
}