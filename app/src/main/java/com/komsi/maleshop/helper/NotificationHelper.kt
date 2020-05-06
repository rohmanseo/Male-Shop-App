package com.komsi.maleshop.helper

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.komsi.maleshop.R
import com.komsi.maleshop.receiver.AlarmReceiver
import com.komsi.maleshop.ui.activity.MainActivity
import java.util.*


object NotificationHelper {
    fun showNotification(
            context: Context,
            notifId: Int,
            title: String,
            message: String,
            channelId: String,
            channelName: String
    ) {

        val notifManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val intent = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), 0)
        val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setGroupSummary(true)
                .setSound(alarmSound)
                .setContentIntent(intent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.enableVibration(false)
            builder.setChannelId(channelId)
            notifManager.createNotificationChannel(channel)
        }
        val notif = builder.build()
        notifManager.notify(notifId, notif)

    }

    fun setupDailyReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 7)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)


        if (Calendar.getInstance().after(calendar)) {
            calendar.add(Calendar.DATE, 1)
        }

        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.EXTRA_NOTIF_ID, 100)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
        )
    }

    fun stopDailyReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.EXTRA_NOTIF_ID, 100)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        alarmManager.cancel(pendingIntent)
    }
}