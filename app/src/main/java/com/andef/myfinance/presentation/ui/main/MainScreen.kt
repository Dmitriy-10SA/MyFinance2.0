package com.andef.myfinance.presentation.ui.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.andef.myfinance.navigation.main.MainAppNavGraph
import com.andef.myfinance.navigation.main.rememberNavigationState
import com.andef.myfinance.presentation.ui.datepicker.MyFinanceDatePicker
import com.andef.myfinance.presentation.ui.expense.ExpensesCheckScreen
import com.andef.myfinance.presentation.ui.income.IncomesCheckScreen
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModelFactory: ViewModelFactory) {
    val navigationState = rememberNavigationState()
    val topBarState = remember {
        mutableStateOf(TopNavigationItem.Today as TopNavigationItem)
    }

    val startDateState = remember { mutableStateOf(Date()) }
    val endDateState = remember { mutableStateOf(Date()) }

    val datePickerVisible = remember { mutableStateOf(false) }

    LaunchedEffect(topBarState.value) {
        when (topBarState.value) {
            TopNavigationItem.Month -> {
                endDateState.value = Date()
                startDateState.value = LocalDate.now().minusMonths(1).toDate()
            }

            TopNavigationItem.Period -> {
                if (!datePickerVisible.value) {
                    datePickerVisible.value = true
                }
            }

            TopNavigationItem.Today -> {
                endDateState.value = Date()
                startDateState.value = Date()
            }

            TopNavigationItem.Week -> {
                endDateState.value = Date()
                startDateState.value = LocalDate.now().minusWeeks(1).toDate()
            }

            TopNavigationItem.Year -> {
                endDateState.value = Date()
                startDateState.value = LocalDate.now().minusYears(1).toDate()
            }
        }
    }

    if (datePickerVisible.value) {
        Scaffold {
            MyFinanceDatePicker(
                paddingValues = it,
                onCloseClickListener = {
                    datePickerVisible.value = false
                },
                onSaveClickListener = { start, end ->
                    endDateState.value = Date(end)
                    startDateState.value = Date(start)
                    datePickerVisible.value = false
                }
            )
        }
    } else {
        Scaffold(
            bottomBar = {
                MainBottomNavigation(navigationState)
            },
            topBar = {
                MainTopBar(topBarState, {}, {
                    datePickerVisible.value = true
                })
            }
        ) { paddingValues ->
            MainAppNavGraph(
                navHostController = navigationState.navHostController,
                incomesScreenContent = {
                    IncomesCheckScreen(
                        viewModelFactory = viewModelFactory,
                        startDate = startDateState.value,
                        endDate = endDateState.value,
                        paddingValues = paddingValues,
                        {}, {}
                    )
                },
                expensesScreenContent = {
                    ExpensesCheckScreen(
                        viewModelFactory = viewModelFactory,
                        startDate = startDateState.value,
                        endDate = endDateState.value,
                        paddingValues = paddingValues,
                        {}, {}
                    )
                },
                totalsScreenContent = {

                }
            )
        }
    }
}

private fun LocalDate.toDate(): Date {
    return Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
}