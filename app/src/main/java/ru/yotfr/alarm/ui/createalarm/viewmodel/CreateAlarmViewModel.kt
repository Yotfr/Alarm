package ru.yotfr.alarm.ui.createalarm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.model.AlarmModel
import ru.yotfr.model.Snooze
import ru.yotfr.model.Sound
import ru.yotfr.domain.ChangeAlarmTriggerTimeUseCase
import ru.yotfr.domain.CreateNewAlarmUseCase
import ru.yotfr.domain.GetAlarmByIdUseCase
import ru.yotfr.alarm.ui.createalarm.event.CreateAlarmEvent
import ru.yotfr.alarm.ui.createalarm.event.CreateAlarmScreenEvent
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class CreateAlarmViewModel @Inject constructor(
    private val createNewAlarmUseCase: ru.yotfr.domain.CreateNewAlarmUseCase,
    private val changeAlarmTriggerTimeUseCase: ru.yotfr.domain.ChangeAlarmTriggerTimeUseCase,
    private val getAlarmByIdUseCase: ru.yotfr.domain.GetAlarmByIdUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow(ru.yotfr.model.AlarmModel())
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

            is CreateAlarmEvent.OnLabelChanged -> {
                labelChanged(event.newLabel)
            }

            is CreateAlarmEvent.OnSnoozeChanged -> {
                snoozeChanged(event.snooze)
            }

            CreateAlarmEvent.WeekDaysCleared -> {
                clearWeekDays()
            }

            is CreateAlarmEvent.OnSoundChanged -> {
                soundChanged(event.newSound)
            }

            is CreateAlarmEvent.OnVibrateChanged -> {
                vibrateChanged(event.newVibrate)
            }

            is CreateAlarmEvent.SoundLevelChanged -> {
                soundLevelChanged(event.newSoundLevel)
            }
        }
    }

    private fun soundLevelChanged(soundLevel: Float) {
        _screenState.update {
            it.copy(
                volume = soundLevel
            )
        }
    }

    private fun soundChanged(sound: ru.yotfr.model.Sound) {
        _screenState.update {
            it.copy(
                sound = sound
            )
        }
    }

    private fun vibrateChanged(vibrate: Boolean) {
        _screenState.update {
            it.copy(
                vibrate = vibrate
            )
        }
    }

    private fun enterScreen(id: Long?) {
        Log.d("TEST","Entering Screen")
        id?.let {
            viewModelScope.launch {
                _screenState.value = getAlarmByIdUseCase(it)
            }
        }
    }
    private fun onWeekDayClicked(weekDay: DayOfWeek) {
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

    private fun clearWeekDays() {
        _screenState.update {
            it.copy(
                activeDays = emptyList()
            )
        }
        timeChanged(_screenState.value.triggerTime.toLocalTime())
    }

    private fun labelChanged(label: String) {
        _screenState.update {
            it.copy(
                label = label
            )
        }
    }

    private fun snoozeChanged(snooze: ru.yotfr.model.Snooze) {
        _screenState.update {
            it.copy(
                snooze = snooze
            )
        }
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
            } while (!_screenState.value.activeDays.contains(nowDate.dayOfWeek))
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
            while (!_screenState.value.activeDays.contains(nowDate.dayOfWeek)) {
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