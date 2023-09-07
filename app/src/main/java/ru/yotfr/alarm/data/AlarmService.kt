package ru.yotfr.alarm.data

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import ru.yotfr.alarm.MainActivity
import ru.yotfr.alarm.R
import ru.yotfr.alarm.ui.navigation.MY_URI_RING

class AlarmService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            if (intent.getBooleanExtra("CANCEL", false)) {
                stopSelf()
            }
        }
        val notification = NotificationCompat.Builder(this, "NOTIFICATION_CHANNEL_SERVICE")
            .setContentTitle("Alarm")
            .setContentText("Ringing...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .addAction(androidx.core.R.drawable.ic_call_decline, "", getCancelIntent())
            .setAutoCancel(true)
            .build()
        val startScreenIntent = Intent(
            Intent.ACTION_VIEW,
            MY_URI_RING.toUri(),
            this,
            MainActivity::class.java
        ).addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        )
        startActivity(startScreenIntent)
        startForeground(3, notification)
        return START_STICKY
    }

    private fun getCancelIntent(): PendingIntent {
        val intentSelf = Intent(this, AlarmService::class.java).apply {
            putExtra("CANCEL", true)
        }
        return PendingIntent.getService(this, 0, intentSelf, PendingIntent.FLAG_IMMUTABLE)
    }

}