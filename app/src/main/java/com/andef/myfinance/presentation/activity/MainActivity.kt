package com.andef.myfinance.presentation.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.andef.myfinance.R
import com.andef.myfinance.presentation.app.MyFinanceApplication
import com.andef.myfinance.presentation.ui.main.MainScreen
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import com.andef.myfinance.ui.theme.MyFinanceTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as MyFinanceApplication).component
    }

    private lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        settings = getSharedPreferences(PREFS_THEME_FILE, MODE_PRIVATE)
        setContent {
            val isDarkTheme = remember {
                mutableStateOf(settings.getBoolean(PREF_IS_DARK_THEME, false))
            }
            MyFinanceTheme(darkTheme = isDarkTheme.value, dynamicColor = false) {
                MainScreen(
                    viewModelFactory = viewModelFactory,
                    onNetworkError = {
                        Toast.makeText(
                            this,
                            getString(R.string.check_ethernet),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    isDarkTheme = isDarkTheme.value,
                    onCheckedChangeClickListener = { isChecked ->
                        isDarkTheme.value = isChecked
                        val editor = settings.edit()
                        editor.putBoolean(PREF_IS_DARK_THEME, isDarkTheme.value)
                        editor.apply()
                    }
                )
            }
        }
    }

    companion object {
        private const val PREFS_THEME_FILE = "theme"
        private const val PREF_IS_DARK_THEME = "is_dark"
    }
}