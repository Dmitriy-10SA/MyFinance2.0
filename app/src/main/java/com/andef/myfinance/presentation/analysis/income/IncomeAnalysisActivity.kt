package com.andef.myfinance.presentation.analysis.income

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andef.myfinance.MyFinanceApplication
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.presentation.currency.CurrencyActivity
import com.andef.myfinance.presentation.currency.CurrencyActivity.Companion
import com.andef.myfinance.ui.theme.MyFinanceTheme
import javax.inject.Inject

class IncomeAnalysisActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyFinanceApplication).component.inject(this)
        val isDarkTheme = intent.getBooleanExtra(IS_DARK_THEME_EXTRA, false)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFinanceTheme(darkTheme = isDarkTheme, dynamicColor = false) {
                IncomeAnalysisScreen(
                    isDarkTheme = isDarkTheme,
                    viewModelFactory = viewModelFactory,
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
        fun newIntent(context: Context, isDarkTheme: Boolean): Intent {
            return Intent(context, IncomeAnalysisActivity::class.java).apply {
                putExtra(IS_DARK_THEME_EXTRA, isDarkTheme)
            }
        }

        private const val IS_DARK_THEME_EXTRA = "isDarkTheme"
    }
}