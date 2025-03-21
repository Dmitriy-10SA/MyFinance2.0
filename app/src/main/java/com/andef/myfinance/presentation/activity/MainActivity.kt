package com.andef.myfinance.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.andef.myfinance.ui.theme.MyFinanceTheme

class MainActivity : ComponentActivity() {
    private val settings by lazy {
        getSharedPreferences(PREFS_THEME_FILE, MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val isDarkTheme = remember {
                mutableStateOf(settings.getBoolean(PREF_IS_DARK_THEME, false))
            }
            MyFinanceTheme(darkTheme = isDarkTheme.value, dynamicColor = false) {

            }
        }
    }

    companion object {
        private const val PREFS_THEME_FILE = "theme"
        private const val PREF_IS_DARK_THEME = "is_dark"
    }
}