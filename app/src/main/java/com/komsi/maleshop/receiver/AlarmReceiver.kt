package com.komsi.maleshop.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.komsi.maleshop.helper.NotificationHelper


class AlarmReceiver : BroadcastReceiver() {
    companion object {
        const val EXTRA_NOTIF_ID = "notifid"
        const val NOTIF_ID = 100
        const val NOTIF_TITLE = "Male Shop"
        const val NOTIF_DESC = "Dont forget to check our new products"
    }

    override fun onReceive(p0: Context, p1: Intent?) {
        NotificationHelper.showNotification(p0, NOTIF_ID, NOTIF_TITLE, NOTIF_DESC, EXTRA_NOTIF_ID, EXTRA_NOTIF_ID)
    }
}