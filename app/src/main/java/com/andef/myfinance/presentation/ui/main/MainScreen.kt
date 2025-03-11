package com.andef.myfinance.presentation.ui.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.andef.myfinance.navigation.main.MainAppNavGraph
import com.andef.myfinance.navigation.main.rememberNavigationState
import com.andef.myfinance.presentation.ui.datepicker.MyFinanceRangeDatePicker
import com.andef.myfinance.presentation.ui.expense.ExpenseScreen
import com.andef.myfinance.presentation.ui.expense.ExpensesCheckScreen
import com.andef.myfinance.presentation.ui.income.IncomeScreen
import com.andef.myfinance.presentation.ui.income.IncomesCheckScreen
import com.andef.myfinance.presentation.utils.toDate
import com.andef.myfinance.presentation.utils.toStartOfDay
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import java.time.LocalDate
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(viewModelFactory: ViewModelFactory) {
    val navigationState = rememberNavigationState()
    val topBarState = remember {
        mutableStateOf(TopNavigationItem.Today as TopNavigationItem)
    }

    val startDateState = remember { mutableStateOf(Date()) }
    val endDateState = remember { mutableStateOf(Date()) }

    val state =
        remember { mutableStateOf(MainScreenState.AnyScreenWithTopAndBottomNav as MainScreenState) }

    LaunchedEffect(topBarState.value) {
        when (topBarState.value) {
            TopNavigationItem.Month -> {
                endDateState.value = Date()
                startDateState.value = LocalDate.now().minusMonths(1).toDate()
            }

            TopNavigationItem.Period -> {
                if (state.value != MainScreenState.DatePickerScreen) {
                    state.value = MainScreenState.DatePickerScreen
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

    AnimatedContent(
        targetState = state.value,
        transitionSpec = {
            if (
                targetState is MainScreenState.IncomeScreenForAdd ||
                targetState is MainScreenState.ExpenseScreenForAdd ||
                targetState is MainScreenState.ExpenseScreenForChange ||
                targetState is MainScreenState.IncomeScreenForChange ||
                targetState is MainScreenState.DatePickerScreen
                ) {
                (slideInHorizontally { it } + fadeIn())
                    .togetherWith(slideOutHorizontally { -it } + fadeOut())
            } else {
                (slideInHorizontally { -it } + fadeIn())
                    .togetherWith(slideOutHorizontally { it } + fadeOut())
            }

        }
    ) { screenState ->
        when (screenState) {
            MainScreenState.AnyScreenWithTopAndBottomNav -> {
                Scaffold(
                    bottomBar = {
                        MainBottomNavigation(navigationState = navigationState)
                    },
                    topBar = {
                        MainTopBar(
                            state = topBarState,
                            onMenuClickListener = {
                                TODO()
                            },
                            onPeriodItemClickListener = {
                                state.value = MainScreenState.DatePickerScreen
                            }
                        )
                    }
                ) { paddingValues ->
                    MainAppNavGraph(
                        navHostController = navigationState.navHostController,
                        incomesScreenContent = {
                            IncomesCheckScreen(
                                viewModelFactory = viewModelFactory,
                                startDate = startDateState.value.toStartOfDay(),
                                endDate = endDateState.value.toStartOfDay(),
                                paddingValues = paddingValues,
                                onIncomeClickListener = { income ->
                                    state.value = MainScreenState.IncomeScreenForChange(income)
                                },
                                onFABClickListener = {
                                    state.value = MainScreenState.IncomeScreenForAdd
                                }
                            )
                        },
                        expensesScreenContent = {
                            ExpensesCheckScreen(
                                viewModelFactory = viewModelFactory,
                                startDate = startDateState.value,
                                endDate = endDateState.value,
                                paddingValues = paddingValues,
                                onExpenseClickListener = { expense ->
                                    state.value = MainScreenState.ExpenseScreenForChange(expense)
                                },
                                onFABClickListener = {
                                    state.value = MainScreenState.ExpenseScreenForAdd
                                }
                            )
                        },
                        totalsScreenContent = {
                            TODO()
                        }
                    )
                }
            }

            MainScreenState.DatePickerScreen -> {
                Scaffold {
                    MyFinanceRangeDatePicker(
                        paddingValues = it,
                        onCloseClickListener = {
                            state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                        },
                        onSaveClickListener = { start, end ->
                            endDateState.value = Date(end)
                            startDateState.value = Date(start)
                            state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                        }
                    )
                }
            }

            MainScreenState.IncomeScreenForAdd -> {
                IncomeScreen(
                    viewModelFactory = viewModelFactory,
                    onBackHandlerClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    }
                )
            }

            MainScreenState.ExpenseScreenForAdd -> {
                ExpenseScreen(
                    viewModelFactory = viewModelFactory,
                    onBackHandlerClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    }
                )
            }

            is MainScreenState.ExpenseScreenForChange -> {
                ExpenseScreen(
                    isAddMode = false,
                    expense = screenState.expense,
                    viewModelFactory = viewModelFactory,
                    onBackHandlerClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    }
                )
            }

            is MainScreenState.IncomeScreenForChange -> {
                IncomeScreen(
                    isAddMode = false,
                    income = screenState.income,
                    viewModelFactory = viewModelFactory,
                    onBackHandlerClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    }
                )
            }
        }
    }
}