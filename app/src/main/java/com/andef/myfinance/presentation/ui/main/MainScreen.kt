package com.andef.myfinance.presentation.ui.main

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.andef.myfinance.navigation.main.MainAppNavGraph
import com.andef.myfinance.navigation.main.rememberNavigationState
import com.andef.myfinance.presentation.ui.income.IncomesCheckScreen
import com.andef.myfinance.ui.theme.MyFinanceTheme
import java.util.Date

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    val topBarState = remember {
        mutableStateOf(TopNavigationItem.Today as TopNavigationItem)
    }
    Scaffold(
        bottomBar = {
            MainBottomNavigation(navigationState)
        },
        topBar = {
            MainTopBar(topBarState, {}, {})
        }
    ) { paddingValues ->
        MainAppNavGraph(
            navHostController = navigationState.navHostController,
            incomesScreenContent = {
                when(topBarState.value) {
                    TopNavigationItem.Month -> TODO()
                    TopNavigationItem.Period -> TODO()
                    TopNavigationItem.Today -> {
                        val startDate = Date()
                        val endDate = Date()
                        IncomesCheckScreen(
                            startDate = startDate,
                            endDate = endDate,
                            paddingValues = paddingValues,
                            {}, {}
                        )
                    }
                    TopNavigationItem.Week -> TODO()
                    TopNavigationItem.Year -> TODO()
                }
            },
            expensesScreenContent = {
                Text("EXPENSE")
            },
            totalsScreenContent = {
                Text("TOTALS")
            }
        )
    }
}

@Preview
@Composable
private fun DarkMainScreenTest() {
    MyFinanceTheme(darkTheme = true) {
        MainScreen()
    }
}

@Preview
@Composable
private fun LightMainScreenTest() {
    MyFinanceTheme(darkTheme = false) {
        MainScreen()
    }
}