package com.andef.myfinance.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.MyFinanceApplication
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.presentation.analysis.income.IncomeAnalysisActivity
import com.andef.myfinance.presentation.analysis.income.IncomeAnalysisScreen
import com.andef.myfinance.presentation.currency.CurrencyActivity
import com.andef.myfinance.presentation.expense.ExpenseActivity
import com.andef.myfinance.presentation.income.IncomeActivity
import com.andef.myfinance.presentation.webview.WebViewActivity
import com.andef.myfinance.presentation.webview.WebViewScreen
import com.andef.myfinance.ui.theme.MyFinanceTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyFinanceApplication).component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainActivityViewModel = viewModel(factory = viewModelFactory)
            val isDarkTheme = remember { mutableStateOf(viewModel.isDarkTheme()) }
            MyFinanceTheme(darkTheme = isDarkTheme.value, dynamicColor = false) {
                MainScreen(
                    isDarkTheme = isDarkTheme.value,
                    viewModelFactory = viewModelFactory,
                    onIncomeFABClickListener = {
                        openIncomeActivity(isDarkTheme.value)
                    },
                    onExpenseFABClickListener = {
                        openExpenseActivity(isDarkTheme.value)
                    },
                    onIncomeClickListener = { income ->
                        openIncomeActivity(isDarkTheme.value, income.id)
                    },
                    onExpenseClickListener = { expense ->
                        openExpenseActivity(isDarkTheme.value, expense.id)
                    },
                    onCheckedChangeClickListener = {
                        viewModel.changeThemeUseCase()
                        isDarkTheme.value = viewModel.isDarkTheme()
                    },
                    onIncomeAnalysisClickListener = {
                        openIncomeAnalysisActivity(isDarkTheme.value)
                    },
                    onExpenseAnalysisClickListener = {

                    },
                    onCurrencyValueClickListener = {
                        openCurrencyActivity(isDarkTheme.value)
                    },
                    onWebViewActionClickListener = { link ->
                        openWebViewActivity(isDarkTheme.value, link)
                    }
                )
            }
        }
    }

    private fun openIncomeAnalysisActivity(isDarkTheme: Boolean) {
        IncomeAnalysisActivity.newIntent(this, isDarkTheme).apply {
            startActivity(this)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_top, 0)
        }
    }

    private fun openWebViewActivity(isDarkTheme: Boolean, link: String) {
        WebViewActivity.newIntent(this, isDarkTheme, link).apply {
            startActivity(this)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_top, 0)
        }
    }

    private fun openCurrencyActivity(isDarkTheme: Boolean) {
        CurrencyActivity.newIntent(this, isDarkTheme).apply {
            startActivity(this)
            @Suppress("DEPRECATION")
            overridePendingTransition(R.anim.slide_in_top, 0)
        }
    }

    private fun openIncomeActivity(isDarkTheme: Boolean, id: Int? = null) {
        val intent = if (id != null) {
            IncomeActivity.newIntent(this, isDarkTheme, id)
        } else {
            IncomeActivity.newIntent(this, isDarkTheme)
        }
        startActivity(intent)
        @Suppress("DEPRECATION")
        overridePendingTransition(R.anim.slide_in_bottom, 0)
    }

    private fun openExpenseActivity(isDarkTheme: Boolean, id: Int? = null) {
        val intent = if (id != null) {
            ExpenseActivity.newIntent(this, isDarkTheme, id)
        } else {
            ExpenseActivity.newIntent(this, isDarkTheme)
        }
        startActivity(intent)
        @Suppress("DEPRECATION")
        overridePendingTransition(R.anim.slide_in_bottom, 0)
    }
}