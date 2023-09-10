package ru.yotfr.alarm.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import ru.yotfr.alarm.data.service.AlarmService

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.let { intent ->
                val id = intent.getLongExtra("ID",0L)
                Log.d("TEST","BROADCAST GET ID $id")
                id.takeIf { it != 0L }?.let { id ->
                    val serviceIntent = Intent(context, AlarmService::class.java)
                        .putExtra("ID", id)
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        context.startForegroundService(serviceIntent)
                    } else {
                        context.startService(serviceIntent)
                    }
                }
            }
        }
    }
}