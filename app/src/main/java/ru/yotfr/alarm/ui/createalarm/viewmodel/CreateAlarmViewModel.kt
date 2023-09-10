package ru.yotfr.alarm.ui.createalarm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.usecase.ChangeAlarmTriggerTimeUseCase
import ru.yotfr.alarm.domain.usecase.CreateNewAlarmUseCase
import ru.yotfr.alarm.domain.usecase.GetAlarmByIdUseCase
import ru.yotfr.alarm.domain.model.WeekDays
import ru.yotfr.alarm.ui.createalarm.event.CreateAlarmEvent
import ru.yotfr.alarm.ui.createalarm.event.CreateAlarmScreenEvent
import ru.yotfr.alarm.ui.createalarm.mapper.toDayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class CreateAlarmViewModel @Inject constructor(
    private val createNewAlarmUseCase: CreateNewAlarmUseCase,
    private val changeAlarmTriggerTimeUseCase: ChangeAlarmTriggerTimeUseCase,
    private val getAlarmByIdUseCase: GetAlarmByIdUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow(AlarmModel())
    val screenState = _screenState.asStateFlow()

    private val _event = Channel<CreateAlarmScreenEvent>()
    val event = _event.receiveAsFlow()

    fun onEvent(event: CreateAlarmEvent) {
        when(event) {
            is CreateAlarmEvent.EnterScreen -> {
                enterScreen(event.id)
            }

            is CreateAlarmEvent.OnWeekDayClicked -> {
                onWeekDayClicked(event.weekDay)
            }

            is CreateAlarmEvent.OnTriggerTimeChanged -> {
                timeChanged(event.localTime)
            }

            CreateAlarmEvent.SaveClicked -> {
                saveChanges()
            }
        }
    }

    private fun enterScreen(id: Long?) {
        id?.let {
            viewModelScope.launch {
                _screenState.value = getAlarmByIdUseCase(it)
            }
        }
    }
    private fun onWeekDayClicked(weekDay: WeekDays) {
        _screenState.update {
            it.copy(
                activeDays = if (it.activeDays.contains(weekDay)) {
                    it.activeDays.toMutableList().minus(weekDay).toList()
                } else {
                    it.activeDays.toMutableList().plus(weekDay).toList()
                }
            )
        }
        timeChanged(_screenState.value.triggerTime.toLocalTime())
    }

    private fun timeChanged(triggerTime: LocalTime) {
        val nowDateTime = LocalDateTime.now()
        val nowTime = nowDateTime.toLocalTime()
        if (triggerTime.isBefore(nowTime)) {
            if (_screenState.value.activeDays.isEmpty()) {
                val nextDay = LocalDate.now().plusDays(1)
                _screenState.update {
                    it.copy(
                        triggerTime = triggerTime.atDate(nextDay)
                    )
                }
                return
            }
            var nowDate = LocalDate.now()
            do {
                nowDate = nowDate.plusDays(1)
            } while (!_screenState.value.activeDays.map { it.toDayOfWeek() }.contains(nowDate.dayOfWeek))
            _screenState.update {
                it.copy(
                    triggerTime = triggerTime.atDate(nowDate)
                )
            }
            return
        } else {
            if (_screenState.value.activeDays.isEmpty()) {
                _screenState.update {
                    it.copy(
                        triggerTime = triggerTime.atDate(LocalDate.now())
                    )
                }
                return
            }
            var nowDate = LocalDate.now()
            while (!_screenState.value.activeDays.map { it.toDayOfWeek() }.contains(nowDate.dayOfWeek)) {
                nowDate = nowDate.plusDays(1)
            }
            _screenState.update {
                it.copy(
                    triggerTime = triggerTime.atDate(nowDate)
                )
            }
            return
        }
    }

    private fun saveChanges() {
        viewModelScope.launch {
            if (_screenState.value.id == 0L) {
                // Means that alarm is new
                createNewAlarmUseCase(_screenState.value)
                _event.send(CreateAlarmScreenEvent.NavigateBack)
            } else {
                changeAlarmTriggerTimeUseCase(_screenState.value)
                _event.send(CreateAlarmScreenEvent.NavigateBack)
            }
        }
    }
}