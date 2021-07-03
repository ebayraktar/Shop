package com.bayraktar.shop

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.res.Configuration
import android.os.Build

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SCREEN_SIZE = resources.configuration.screenLayout and
                Configuration.SCREENLAYOUT_SIZE_MASK
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 = NotificationChannel(
                CHANNEL_1_ID,
                getString(R.string.notification_channel),
                NotificationManager.IMPORTANCE_HIGH
            )
            channel1.description = getString(R.string.notification_channel)
            val manager = getSystemService(
                NotificationManager::class.java
            )!!
            manager.createNotificationChannel(channel1)
        }
    }

    companion object {
        const val CHANNEL_1_ID = "Channel1"
        var SCREEN_SIZE: Int = Configuration.SCREENLAYOUT_SIZE_NORMAL
    }
}