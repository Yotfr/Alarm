package ru.yotfr.alarm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.alarm.domain.AlarmModel
import ru.yotfr.alarm.domain.GetAllAlarmsUseCase
import ru.yotfr.alarm.domain.ToggleAlarmStatusUseCase
import ru.yotfr.alarm.ui.state.AlarmsListScreenState
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(
    private val getAllAlarmsUseCase: GetAllAlarmsUseCase,
    private val toggleAlarmStatusUseCase: ToggleAlarmStatusUseCase
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

    fun toggleAlarm(alarmModel: AlarmModel, value: Boolean) {
        viewModelScope.launch {
            toggleAlarmStatusUseCase(alarmModel, value)
        }
    }

}