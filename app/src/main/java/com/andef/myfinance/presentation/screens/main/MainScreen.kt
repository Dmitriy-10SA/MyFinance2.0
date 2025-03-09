package com.andef.myfinance.presentation.screens.main

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.andef.myfinance.navigation.main.MainAppNavGraph
import com.andef.myfinance.navigation.main.rememberNavigationState
import com.andef.myfinance.presentation.screens.main.bottom.MainBottomNavigation
import com.andef.myfinance.presentation.screens.main.top.MainTopBar
import com.andef.myfinance.presentation.screens.main.top.TopNavigationItem
import com.andef.myfinance.ui.theme.MyFinanceTheme

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
    ) {
        MainAppNavGraph(
            navHostController = navigationState.navHostController,
            incomesScreenContent = {
                Text("INCOME")
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