package com.andef.myfinance.presentation.expense

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andef.myfinance.MyFinanceApplication
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.ui.theme.MyFinanceTheme
import javax.inject.Inject

class ExpenseActivity : ComponentActivity() {
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
                ExpenseScreen(
                    isDarkTheme = isDarkTheme,
                    id = id,
                    onBackClickListener = {
                        finish()
                        @Suppress("DEPRECATION")
                        overridePendingTransition(0, R.anim.slide_out_bottom)
                    },
                    viewModelFactory = viewModelFactory
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context, isDarkTheme: Boolean, id: Int? = null): Intent {
            return Intent(context, ExpenseActivity::class.java).apply {
                putExtra(IS_DARK_THEME_EXTRA, isDarkTheme)
                id?.let { putExtra(ID_EXTRA, it) }
            }
        }

        private const val IS_DARK_THEME_EXTRA = "isDarkTheme"
        private const val ID_EXTRA = "id"
    }
}