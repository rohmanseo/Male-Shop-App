package com.komsi.maleshop.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.komsi.maleshop.R
import com.komsi.maleshop.helper.NotificationHelper


class AlarmReceiver : BroadcastReceiver() {
    companion object {
        const val EXTRA_NOTIF_ID = "notifid"
        const val NOTIF_ID = 100
        const val NOTIF_TITLE = "Male Shop"
    }

    override fun onReceive(p0: Context, p1: Intent?) {
        val notifMessage = p0.getString(R.string.notif_message)
        NotificationHelper.showNotification(p0, NOTIF_ID, NOTIF_TITLE, notifMessage, EXTRA_NOTIF_ID, EXTRA_NOTIF_ID)
    }
}