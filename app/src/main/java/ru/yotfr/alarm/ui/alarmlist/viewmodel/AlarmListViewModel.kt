package ru.yotfr.alarm.ui.alarmlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.usecase.DeleteAlarmUseCase
import ru.yotfr.alarm.domain.usecase.GetAllAlarmsUseCase
import ru.yotfr.alarm.domain.usecase.ToggleAlarmStatusUseCase
import ru.yotfr.alarm.ui.alarmlist.event.AlarmListEvent
import ru.yotfr.alarm.ui.alarmlist.state.AlarmsListScreenState
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(
    private val getAllAlarmsUseCase: GetAllAlarmsUseCase,
    private val toggleAlarmStatusUseCase: ToggleAlarmStatusUseCase,
    private val deleteAlarmUseCase: DeleteAlarmUseCase
) : ViewModel() {
    private val _screenState = MutableStateFlow(AlarmsListScreenState())
    val screenState = _screenState.asStateFlow()
    init {
        viewModelScope.launch {
            getAllAlarmsUseCase().collectLatest { alarmsList ->
                _screenState.update { screenState ->
                    screenState.copy(
                        alarms = alarmsList
                    )
                }
            }
        }
    }

    fun onEvent(event: AlarmListEvent) {
        when(event) {
            is AlarmListEvent.DeleteAlarm -> {
                deleteAlarm(event.alarmModel)
            }
            is AlarmListEvent.ToggleAlarm -> {
                toggleAlarm(event.alarmModel, event.newValue)
            }
        }
    }

    private fun toggleAlarm(alarmModel: AlarmModel, value: Boolean) {
        viewModelScope.launch {
            toggleAlarmStatusUseCase(alarmModel, value)
        }
    }

    private fun deleteAlarm(alarmModel: AlarmModel) {
        viewModelScope.launch {
            deleteAlarmUseCase(alarmModel)
        }
    }

}