package com.andef.myfinance.presentation.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.navigation.AppNavGraph
import com.andef.myfinance.navigation.Screen
import com.andef.myfinance.navigation.rememberNavigationState
import com.andef.myfinance.old_presentation.toDate
import com.andef.myfinance.presentation.datepickers.MyFinanceRangeDatePicker
import java.time.LocalDate
import java.util.Date

@Composable
fun MainScreen(
    isDarkTheme: Boolean,
    viewModelFactory: ViewModelFactory,
    onThemeChangeClickListener: () -> Unit
) {
    val navigationState = rememberNavigationState()
    val topBarState = remember {
        mutableStateOf(MainTopNavigationItem.Today as MainTopNavigationItem)
    }
    val startDate = remember { mutableStateOf(Date()) }
    val endDate = remember { mutableStateOf(Date()) }

    LaunchedEffect(topBarState.value) {
        when (topBarState.value) {
            MainTopNavigationItem.Today -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().toDate()
            }

            MainTopNavigationItem.Week -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().minusWeeks(1).toDate()
            }

            MainTopNavigationItem.Month -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().minusMonths(1).toDate()
            }

            MainTopNavigationItem.Year -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().minusYears(1).toDate()
            }

            MainTopNavigationItem.Period -> {}
        }
    }

    Scaffold(
        topBar = {
            MainTopBar(
                topBarState = topBarState,
                navigationState = navigationState,
                onPeriodTabClickListener = {
                    navigationState.navigate(Screen.RangeDatePickerScreen.route)
                },
                onNavigationMenuIconClickListener = {
                    // TODO("open sheet")
                }
            )
        },
        bottomBar = {
            MainBottomBar(
                navigationState = navigationState
            )
        },
        floatingActionButton = {
            MainFloatingActionButton(
                navigationState = navigationState,
                isDarkTheme = isDarkTheme
            )
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            incomesScreenContent = {

            },
            expensesScreenContent = {

            },
            totalsScreenContent = {

            },
            rangeDatePickerScreen = {
                MyFinanceRangeDatePicker(
                    isDarkTheme = isDarkTheme,
                    onCloseClickListener = {
                        navigationState.popBackStack()
                    },
                    onActionDateRangePickerIconClickListener = { start, end ->
                        startDate.value = Date(start)
                        endDate.value = Date(end)
                        navigationState.popBackStack()
                    }
                )
            }
        )
    }
}