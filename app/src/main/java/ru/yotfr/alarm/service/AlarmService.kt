package ru.yotfr.alarm.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.yotfr.alarm.AlarmApp
import ru.yotfr.alarm.MainActivity
import ru.yotfr.alarm.R
import ru.yotfr.alarm.domain.model.AlarmModel
import ru.yotfr.alarm.domain.model.Sound
import ru.yotfr.alarm.domain.model.getNextTriggerTime
import ru.yotfr.alarm.domain.usecase.ChangeAlarmTriggerTimeUseCase
import ru.yotfr.alarm.domain.usecase.DismissAlarmUseCase
import ru.yotfr.alarm.domain.usecase.GetAlarmByIdUseCase
import ru.yotfr.alarm.domain.usecase.SnoozeAlarmUseCase
import ru.yotfr.alarm.mediaplayer.AlarmPlayer
import ru.yotfr.alarm.ui.navigation.NavigationConstants
import javax.inject.Inject

@AndroidEntryPoint
class AlarmService : Service() {

    @Inject
    lateinit var getAlarmByIdUseCase: GetAlarmByIdUseCase

    @Inject
    lateinit var changeAlarmTriggerTimeUseCase: ChangeAlarmTriggerTimeUseCase

    @Inject
    lateinit var dismissAlarmUseCase: DismissAlarmUseCase

    @Inject
    lateinit var snoozeAlarmUseCase: SnoozeAlarmUseCase

    private val binder = AlarmBinder()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var alarmPlayer: AlarmPlayer? = null

    private val _alarm = MutableStateFlow<AlarmModel?>(null)
    val alarm = _alarm.asStateFlow()

    private var state: AlarmServiceHelper.AlarmState = AlarmServiceHelper.AlarmState.UNKNOWN

    private val _event = Channel<AlarmServiceEvent>()
    val event = _event.receiveAsFlow()

    override fun onCreate() {
        super.onCreate()
        initializeAlarmPlayer()
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        if (state == AlarmServiceHelper.AlarmState.RINGING) {
            recreateActivity()
        }
        return true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.action?.takeIf { intentAction ->
            AlarmServiceHelper.ServiceCommand.values().map { it.action }.contains(intentAction)
        }?.let { serviceCommandAction ->
            when (AlarmServiceHelper.ServiceCommand.valueOf(serviceCommandAction)) {
                AlarmServiceHelper.ServiceCommand.ACTION_START_SERVICE -> {
                    intent.getLongExtra(
                        AlarmServiceHelper.ALARM_ID_KEY,
                        0L
                    ).takeIf { it != 0L }?.let { alarmId ->
                        scope.launch {
                            _alarm.value = getAlarmByIdUseCase(alarmId).also { alarm ->
                                scheduleNewAlarm(alarm)
                                processStartAction(alarm)
                            }
                        }
                    }
                }

                AlarmServiceHelper.ServiceCommand.ACTION_DISMISS_ALARM -> {
                    processDismissAction()
                }

                AlarmServiceHelper.ServiceCommand.ACTION_SNOOZE_ALARM -> {
                    processSnoozeAction()
                }
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        destroyAlarmPlayer()
    }


    private fun processStartAction(alarmModel: AlarmModel) {
        startForeground(alarmModel.id.toInt(), getServiceNotification(alarmModel))
        state = AlarmServiceHelper.AlarmState.RINGING
        //configureAlarmPlayerSound(alarm.volume)
        //playAlarmSound(alarm.sound)
        startActivity(getStartScreenIntent())
    }

    private fun processSnoozeAction() {
        stopSelf()
        stopForeground(STOP_FOREGROUND_REMOVE)
        scope.launch {
            _alarm.value?.let {
                snoozeAlarmUseCase(
                    alarmModel = it,
                    snoozeMinutes = it.snooze.minutes()
                )
                _event.send(AlarmServiceEvent.AlarmDismissed)
            }
        }
        state = AlarmServiceHelper.AlarmState.SNOOZED
    }

    private fun processDismissAction() {
        stopSelf()
        stopForeground(STOP_FOREGROUND_REMOVE)
        scope.launch {
            _alarm.value?.let {
                dismissAlarmUseCase(it)
            }
            _event.send(AlarmServiceEvent.AlarmDismissed)
        }
        state = AlarmServiceHelper.AlarmState.DISMISSED
    }

    private fun getStartScreenIntent(): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            NavigationConstants.MY_URI_RING.toUri(),
            this,
            MainActivity::class.java
        ).addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK
        )
    }

    private fun getServiceNotification(alarmModel: AlarmModel): Notification {
        return NotificationCompat.Builder(
            this,
            AlarmApp.NOTIFICATION_CHANNEL_SERVICE_NOTIFICATION_ID
        )
            .setContentTitle(alarmModel.label)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .build()
    }

    private fun initializeAlarmPlayer() {
        alarmPlayer = AlarmPlayer(
            isLooping = true,
            context = this
        )
    }

    private fun playAlarmSound(sound: Sound) {
        alarmPlayer?.playSound(sound)
    }

    private fun configureAlarmPlayerSound(volume: Float) {
        alarmPlayer?.configureVolume(volume)
    }

    private fun destroyAlarmPlayer() {
        alarmPlayer?.destroyAlarmPlayer()
    }

    private suspend fun scheduleNewAlarm(alarmModel: AlarmModel) {
        alarmModel.getNextTriggerTime()?.let { nextTriggerTime ->
            changeAlarmTriggerTimeUseCase(
                alarmModel.copy(
                    triggerTime = nextTriggerTime
                )
            )
        }
    }

    private fun recreateActivity() {
        startActivity(getStartScreenIntent())
    }

    inner class AlarmBinder : Binder() {
        fun getService(): AlarmService = this@AlarmService
    }

}