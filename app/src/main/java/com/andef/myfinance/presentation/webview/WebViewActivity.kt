package com.andef.myfinance.presentation.webview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andef.myfinance.R
import com.andef.myfinance.ui.theme.MyFinanceTheme

class WebViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val isDarkTheme = intent.getBooleanExtra(IS_DARK_THEME_EXTRA, false)
        val link = intent.extras?.getString(LINK_EXTRA)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFinanceTheme(darkTheme = isDarkTheme, dynamicColor = false) {
                WebViewScreen(
                    link = link!!,
                    onBackClickListener = {
                        finish()
                        @Suppress("DEPRECATION")
                        overridePendingTransition(0, R.anim.slide_out_top)
                    }
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context, isDarkTheme: Boolean, link: String): Intent {
            return Intent(context, WebViewActivity::class.java).apply {
                putExtra(IS_DARK_THEME_EXTRA, isDarkTheme)
                putExtra(LINK_EXTRA, link)
            }
        }

        private const val IS_DARK_THEME_EXTRA = "isDarkTheme"
        private const val LINK_EXTRA = "link"
    }
}