package com.andef.myfinance

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val notificationManager = ContextCompat.getSystemService(
                it,
                NotificationManager::class.java
            ) as NotificationManager

            createNotificationChannel(it, notificationManager)

            val notification = NotificationCompat.Builder(it, CHANNEL_ID)
                .setContentTitle(ContextCompat.getString(it, R.string.app_name))
                .setSmallIcon(R.drawable.icon)
                .setContentText(intent?.extras?.getString(TEXT_EXTRA) ?: "")
                .setColor(Color.WHITE)
                .build()

            notificationManager.notify(intent?.extras?.getInt(ID_EXTRA) ?: 0, notification)
        }
    }

    private fun createNotificationChannel(
        context: Context,
        notificationManager: NotificationManager
    ) {
        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            ContextCompat.getString(context, R.string.reminder),
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(notificationChannel)
    }

    companion object {
        private const val CHANNEL_ID = "reminder_channel_id"

        fun newIntent(context: Context, id: Int, text: String): Intent {
            return Intent(context, ReminderReceiver::class.java).apply {
                putExtra(ID_EXTRA, id)
                putExtra(TEXT_EXTRA, text)
            }
        }

        private const val ID_EXTRA = "id"
        private const val TEXT_EXTRA = "text"
    }
}