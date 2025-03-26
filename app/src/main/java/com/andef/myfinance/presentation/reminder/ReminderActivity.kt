package com.andef.myfinance.presentation.reminder

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import com.andef.myfinance.MyFinanceApplication
import com.andef.myfinance.R
import com.andef.myfinance.ReminderReceiver
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.ui.theme.MyFinanceTheme
import javax.inject.Inject

class ReminderActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyFinanceApplication).component.inject(this)
        val isDarkTheme = intent.getBooleanExtra(IS_DARK_THEME_EXTRA, false)
        val id = if (intent.hasExtra(ID_EXTRA)) {
            intent.extras?.getInt(ID_EXTRA)
        } else {
            null
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFinanceTheme(darkTheme = isDarkTheme, dynamicColor = false) {
                ReminderScreen(
                    isDarkTheme = isDarkTheme,
                    id = id,
                    onBackClickListener = {
                        finish()
                        @Suppress("DEPRECATION")
                        overridePendingTransition(0, R.anim.slide_out_bottom)
                    },
                    viewModelFactory = viewModelFactory,
                    onAddAction = { id, text, time ->
                        addNotification(id, text, time)
                    },
                    onChangeAction = { id, text, time ->
                        changeNotification(id, text, time)
                    }
                )
            }
        }
    }

    private fun cancelNotification(id: Int) {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = ReminderReceiver.newIntent(this, id, "")
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            id,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
        pendingIntent.cancel()
        val notificationManager = ContextCompat.getSystemService(
            this,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.cancel(id)
    }

    private fun changeNotification(id: Int, text: String, time: Long) {
        cancelNotification(id)
        addNotification(id, text, time)
    }

    private fun addNotification(id: Int, text: String, time: Long) {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = ReminderReceiver.newIntent(this, id, text)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            id,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
    }

    companion object {
        fun newIntent(context: Context, isDarkTheme: Boolean, id: Int? = null): Intent {
            return Intent(context, ReminderActivity::class.java).apply {
                putExtra(IS_DARK_THEME_EXTRA, isDarkTheme)
                id?.let { putExtra(ID_EXTRA, it) }
            }
        }

        private const val IS_DARK_THEME_EXTRA = "isDarkTheme"
        private const val ID_EXTRA = "id"
    }
}