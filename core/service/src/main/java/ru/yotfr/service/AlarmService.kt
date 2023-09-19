package ru.yotfr.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.yotfr.alarm.AlarmApp
import ru.yotfr.alarm.MainActivity
import ru.yotfr.model.AlarmModel
import ru.yotfr.model.getNextTriggerTime
import ru.yotfr.alarm.domain.usecase.ChangeAlarmTriggerTimeUseCase
import ru.yotfr.alarm.domain.usecase.GetAlarmByIdUseCase
import ru.yotfr.alarm.mediaplayer.AlarmPlayer
import ru.yotfr.alarm.ui.navigation.NavigationConstants
import ru.yotfr.domain.ChangeAlarmTriggerTimeUseCase
import ru.yotfr.domain.GetAlarmByIdUseCase
import ru.yotfr.mediaplayer.AlarmPlayer
import ru.yotfr.model.Alarm
import javax.inject.Inject

@AndroidEntryPoint
class AlarmService : Service() {

    @Inject
    lateinit var getAlarmByIdUseCase: GetAlarmByIdUseCase

    @Inject
    lateinit var changeAlarmTriggerTimeUseCase: ChangeAlarmTriggerTimeUseCase

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    private var alarmPlayer: AlarmPlayer? = null
    private var alarmID: Long? = null

    override fun onCreate() {
        super.onCreate()
        initializeAlarmPlayer()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            if (it.getBooleanExtra(RECREATE_ACTIVITY_INTENT_EXTRA_KEY, false)) {
                alarmID?.let { id ->
                    startActivity(getStartScreenIntent(id, true))
                }
            }
            intent.getLongExtra(ALARM_ID_INTENT_EXTRA_KEY, 0L).takeIf { it != 0L }?.let { alarmId ->
                alarmID = alarmId
                scope.launch {
                    val alarm = getAlarmByIdUseCase(alarmId)
                    scheduleNewAlarm(alarm)
                    configureAlarmPlayerSound(alarm.volume)
                    playAlarmSound(alarm.sound)
                }
                startActivity(getStartScreenIntent(alarmId))
                startForeground(alarmId.toInt(), getServiceNotification())

                AlarmRingStatus.isRinging = true
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        AlarmRingStatus.isRinging = false
        job.cancel()
        alarmID = null
        destroyAlarmPlayer()
    }

    private fun getStartScreenIntent(alarmId: Long, recreate: Boolean = false): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            "${NavigationConstants.MY_URI_RING}/${NavigationConstants.ALARM_ID_ARGUMENT_KEY}=$alarmId".toUri(),
            this,
            MainActivity::class.java
        ).addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK or if (recreate) Intent.FLAG_ACTIVITY_SINGLE_TOP else Intent.FLAG_ACTIVITY_CLEAR_TOP
        )
    }

    private fun getServiceNotification(): Notification {
        return NotificationCompat.Builder(
            this,
            AlarmApp.NOTIFICATION_CHANNEL_SERVICE_NOTIFICATION_ID
        )
            .setContentTitle("Alarm")
            .setContentText("Ringing...")
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

    private fun playAlarmSound(sound: ru.yotfr.model.Sound) {
        alarmPlayer?.playSound(sound)
    }

    private fun configureAlarmPlayerSound(volume: Float) {
        alarmPlayer?.configureVolume(volume)
    }

    private fun destroyAlarmPlayer() {
        alarmPlayer?.destroyAlarmPlayer()
    }

    private suspend fun scheduleNewAlarm(alarmModel: Alarm) {
        alarmModel.getNextTriggerTime()?.let { nextTriggerTime ->
            changeAlarmTriggerTimeUseCase(
                alarmModel.copy(
                    triggerTime = nextTriggerTime
                )
            )
        }
    }

    companion object {
        const val ALARM_ID_INTENT_EXTRA_KEY = "ID"
        const val RECREATE_ACTIVITY_INTENT_EXTRA_KEY = "recreate"
    }

}