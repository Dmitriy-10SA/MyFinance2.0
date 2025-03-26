package com.andef.myfinance

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.andef.myfinance.presentation.main.MainActivity

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val notificationManager = ContextCompat.getSystemService(
                it,
                NotificationManager::class.java
            ) as NotificationManager

            createNotificationChannel(it, notificationManager)

            val id = intent?.extras?.getInt(ID_EXTRA) ?: 0

            val pendingIntent = PendingIntent.getActivity(
                it,
                id,
                MainActivity.newIntent(it),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val notification = NotificationCompat.Builder(it, CHANNEL_ID)
                .setContentTitle(ContextCompat.getString(it, R.string.app_name))
                .setSmallIcon(R.drawable.icon)
                .setContentText(intent?.extras?.getString(TEXT_EXTRA) ?: "")
                .setContentIntent(pendingIntent)
                .setColor(Color.WHITE)
                .setAutoCancel(true)
                .build()

            notificationManager.notify(id, notification)
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