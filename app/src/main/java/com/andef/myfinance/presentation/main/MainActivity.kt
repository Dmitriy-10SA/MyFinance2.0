package com.andef.myfinance.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.presentation.income.IncomeActivity
import com.andef.myfinance.MyFinanceApplication
import com.andef.myfinance.ViewModelFactory
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
            MyFinanceTheme(darkTheme = isSystemInDarkTheme(), dynamicColor = false) {
                MainScreen(
                    isDarkTheme = isSystemInDarkTheme(),
                    viewModelFactory = viewModelFactory,
                    onIncomeFABClickListener = {
                        IncomeActivity.newIntent(this, isDarkTheme.value).apply {
                            startActivity(this)
                        }
                    },
                    onExpenseFABClickListener = {

                    },
                    onIncomeClickListener = { income ->
                        IncomeActivity.newIntent(this, isDarkTheme.value, income.id).apply {
                            startActivity(this)
                        }
                    },
                    onExpenseClickListener = { expense ->
                        //TODO()
                    }
                )
            }
        }
    }
}

@Composable
private fun App() {

}