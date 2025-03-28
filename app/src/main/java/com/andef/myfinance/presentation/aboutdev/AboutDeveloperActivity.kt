package com.andef.myfinance.presentation.aboutdev

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andef.myfinance.R
import com.andef.myfinance.ui.theme.MyFinanceTheme

class AboutDeveloperActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val isDarkTheme = intent?.extras?.getBoolean(IS_DARK_THEME_EXTRA) ?: false
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFinanceTheme(darkTheme = isDarkTheme, dynamicColor = false) {
                AboutDeveloperScreen(
                    onCancelClickListener = {
                        finish()
                        @Suppress("DEPRECATION")
                        overridePendingTransition(0, R.anim.slide_out_top)
                    },
                    onVKClickListener = {
                        showVK()
                    },
                    onMailClickListener = {
                        showMail()
                    },
                    onTelegramClickListener = {
                        showTelegram()
                    }
                )
            }
        }
    }

    private fun showTelegram() {
        val telegramIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(TELEGRAM_LINK)
        )
        startActivity(telegramIntent)
    }

    private fun showMail() {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse(MAIL_LINK)
        }
        startActivity(
            Intent.createChooser(
                emailIntent,
                getString(R.string.choose_mail_client)
            )
        )
    }

    private fun showVK() {
        val vkIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(VK_LINK)
        )
        startActivity(vkIntent)
    }

    companion object {
        fun newIntent(context: Context, isDarkTheme: Boolean): Intent {
            return Intent(context, AboutDeveloperActivity::class.java).apply {
                putExtra(IS_DARK_THEME_EXTRA, isDarkTheme)
            }
        }

        private const val IS_DARK_THEME_EXTRA = "isDarkTheme"

        private const val VK_LINK = "https://vk.com/semkin_dmitriy10"
        private const val MAIL_LINK = "mailto:semkin_dmitriy10@vk.com"
        private const val TELEGRAM_LINK = "https://t.me/dsemkin"
    }
}