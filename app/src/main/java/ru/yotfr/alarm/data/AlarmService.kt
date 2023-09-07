package ru.yotfr.alarm.data

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import ru.yotfr.alarm.R

class AlarmService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            if (intent.getBooleanExtra("CANCEL", false)) {
                stopSelf()
            }
        }
        val intentSelf = Intent(this, AlarmService::class.java).apply {
            putExtra("CANCEL", true)
        }
        val pendingIntentSelf = PendingIntent.getService(this, 0, intentSelf, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, "NOTIFICATION_CHANNEL_SERVICE")
            .setContentTitle("Alarm")
            .setContentText("Ringing...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .addAction(androidx.core.R.drawable.ic_call_decline, "", pendingIntentSelf)
            .build()
        startForeground(3, notification)
        return START_STICKY
    }
}