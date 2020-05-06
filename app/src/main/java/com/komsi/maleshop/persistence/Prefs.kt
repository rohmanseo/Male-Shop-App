package com.komsi.maleshop.persistence

import android.content.Context
import com.komsi.maleshop.helper.NotificationHelper

object Prefs {
    fun getDailyReminderSetting(context: Context): Boolean {
        val pref = context.getSharedPreferences("SETTING", Context.MODE_PRIVATE)
        val prefEditor = pref.edit()
        if (!pref.contains("daily")) {
            prefEditor.putBoolean("daily", true)
            prefEditor.apply()
        }
        return pref.getBoolean("daily", true)
    }
    fun changeDailyReminder(context: Context,state: Boolean){
        val pref = context.getSharedPreferences("SETTING", Context.MODE_PRIVATE)
        val prefEditor = pref.edit()
        prefEditor.putBoolean("daily",state)
        prefEditor.apply()
        if (state){
            NotificationHelper.setupDailyReminder(context)
        }else{
            NotificationHelper.stopDailyReminder(context)
        }
    }
}