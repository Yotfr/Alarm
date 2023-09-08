package ru.yotfr.alarm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.yotfr.alarm.domain.AlarmModel
import ru.yotfr.alarm.domain.ChangeAlarmTriggerTimeUseCase
import ru.yotfr.alarm.domain.CreateNewAlarmUseCase
import ru.yotfr.alarm.domain.GetAlarmByIdUseCase
import ru.yotfr.alarm.ui.event.CreateAlarmEvent
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CreateAlarmViewModel @Inject constructor(
    private val createNewAlarmUseCase: CreateNewAlarmUseCase,
    private val changeAlarmTriggerTimeUseCase: ChangeAlarmTriggerTimeUseCase,
    private val getAlarmByIdUseCase: GetAlarmByIdUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<AlarmModel?>(null)
    val screenState = _screenState.asStateFlow()

    private val _event = Channel<CreateAlarmEvent>()
    val event = _event.receiveAsFlow()

    fun enterScreen(id: Long?) {
        id?.let {
            viewModelScope.launch {
                _screenState.value = getAlarmByIdUseCase(it)
            }
        }
    }

    fun applyChanges(
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
        _screenState.value?.let {
            viewModelScope.launch {
                changeAlarmTriggerTimeUseCase(
                    it.copy(
                        triggerTime = alarmTriggerTime
                    )
                )
                _event.send(CreateAlarmEvent.NavigateBack)
            }
        } ?: kotlin.run {
            val newAlarmModel = AlarmModel(
                id = 0,
                triggerTime = alarmTriggerTime,
                isActive = true
            )
            viewModelScope.launch {
                createNewAlarmUseCase(newAlarmModel)
                _event.send(CreateAlarmEvent.NavigateBack)
            }
        }
        viewModelScope.launch {

        }
    }
}