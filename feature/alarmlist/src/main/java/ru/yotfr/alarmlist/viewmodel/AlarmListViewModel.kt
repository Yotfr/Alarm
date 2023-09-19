package ru.yotfr.alarmlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.model.AlarmModel
import ru.yotfr.domain.DeleteAlarmUseCase
import ru.yotfr.domain.GetAllAlarmsUseCase
import ru.yotfr.domain.ToggleAlarmStatusUseCase
import ru.yotfr.alarmlist.event.AlarmListEvent
import ru.yotfr.alarmlist.state.AlarmsListScreenState
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(
    private val getAllAlarmsUseCase: ru.yotfr.domain.GetAllAlarmsUseCase,
    private val toggleAlarmStatusUseCase: ru.yotfr.domain.ToggleAlarmStatusUseCase,
    private val deleteAlarmUseCase: ru.yotfr.domain.DeleteAlarmUseCase
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

    private fun toggleAlarm(alarmModel: ru.yotfr.model.AlarmModel, newState: Boolean) {
        viewModelScope.launch {
            toggleAlarmStatusUseCase(
                alarmModel,
                newState
            )
        }
    }

    private fun deleteAlarm(alarmModel: ru.yotfr.model.AlarmModel) {
        viewModelScope.launch {
            deleteAlarmUseCase(alarmModel)
        }
    }

}