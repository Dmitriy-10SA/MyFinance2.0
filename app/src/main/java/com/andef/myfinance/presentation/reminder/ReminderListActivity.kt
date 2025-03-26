package com.andef.myfinance.presentation.reminder

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import com.andef.myfinance.MyFinanceApplication
import com.andef.myfinance.R
import com.andef.myfinance.ReminderReceiver
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.ui.theme.MyFinanceTheme
import javax.inject.Inject

class ReminderListActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val showDialog = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyFinanceApplication).component.inject(this)
        val isDarkTheme = intent.getBooleanExtra(IS_DARK_THEME_EXTRA, false)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val permissionStatus = getPermissionStatus()
            showDialog.value = isNotGranted(permissionStatus)

            MyFinanceTheme(darkTheme = isDarkTheme, dynamicColor = false) {
                if (showDialog.value) {
                    NotGrantedReminderListScreen(
                        isDarkTheme = isDarkTheme,
                        onAcceptClickListener = {
                            showSettings()
                        },
                        onCancelClickListener = {
                            finishActivity()
                        }
                    )
                } else {
                    GrantedReminderListScreen(
                        viewModelFactory = viewModelFactory,
                        isDarkTheme = isDarkTheme,
                        onFABClickListener = {
                            ReminderActivity.newIntent(this, isDarkTheme).apply {
                                startActivity(this)
                                @Suppress("DEPRECATION")
                                overridePendingTransition(R.anim.slide_in_bottom, 0)
                            }
                        },
                        onReminderClickListener = { reminder ->
                            ReminderActivity.newIntent(this, isDarkTheme, reminder.id).apply {
                                startActivity(this)
                                @Suppress("DEPRECATION")
                                overridePendingTransition(R.anim.slide_in_bottom, 0)
                            }
                        },
                        onCancelClickListener = {
                            finishActivity()
                        },
                        onRemove = { id ->
                            cancelNotification(id)
                        },
                        onInfoClickListener = {

                        }
                    )
                }
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

    private fun showSettings() {
        Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            startActivity(this)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_left, 0)
        }
    }

    private fun finishActivity() {
        finish()
        @Suppress("DEPRECATION")
        overridePendingTransition(0, R.anim.slide_out_top)
    }

    override fun onResume() {
        super.onResume()
        val permissionStatus = getPermissionStatus()
        showDialog.value = isNotGranted(permissionStatus)
    }

    private fun getPermissionStatus(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            )
        } else {
            PackageManager.PERMISSION_GRANTED
        }
    }

    private fun isNotGranted(permissionStatus: Int): Boolean {
        return permissionStatus != PackageManager.PERMISSION_GRANTED
    }

    companion object {
        fun newIntent(context: Context, isDarkTheme: Boolean): Intent {
            return Intent(context, ReminderListActivity::class.java).apply {
                putExtra(IS_DARK_THEME_EXTRA, isDarkTheme)
            }
        }

        private const val IS_DARK_THEME_EXTRA = "isDarkTheme"
    }
}