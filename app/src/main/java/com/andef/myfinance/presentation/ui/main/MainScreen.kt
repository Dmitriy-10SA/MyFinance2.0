package com.andef.myfinance.presentation.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.myfinance.R
import com.andef.myfinance.navigation.main.MainAppNavGraph
import com.andef.myfinance.navigation.main.NavigationState
import com.andef.myfinance.navigation.main.rememberNavigationState
import com.andef.myfinance.presentation.ui.currency.CurrencyScreen
import com.andef.myfinance.presentation.ui.datepicker.MyFinanceRangeDatePicker
import com.andef.myfinance.presentation.ui.expense.ExpenseScreen
import com.andef.myfinance.presentation.ui.expense.ExpensesCheckScreen
import com.andef.myfinance.presentation.ui.income.IncomeScreen
import com.andef.myfinance.presentation.ui.income.IncomesCheckScreen
import com.andef.myfinance.presentation.ui.total.TotalScreen
import com.andef.myfinance.presentation.utils.toDate
import com.andef.myfinance.presentation.utils.toStartOfDay
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import com.andef.myfinance.presentation.web.WebViewScreen
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    viewModelFactory: ViewModelFactory,
    isDarkTheme: Boolean,
    paddingValues: PaddingValues,
    onCheckedChangeClickListener: (Boolean) -> Unit
) {
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
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { screenState ->
        when (screenState) {
            MainScreenState.AnyScreenWithTopAndBottomNav -> {
                AnyScreenWithTopAndBottomNavContent(
                    state = state,
                    navigationState = navigationState,
                    viewModelFactory = viewModelFactory,
                    isDarkTheme = isDarkTheme,
                    topBarState = topBarState,
                    startDateState = startDateState,
                    endDateState = endDateState,
                    onCheckedChangeClickListener = onCheckedChangeClickListener
                )
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
                        },
                        isDarkTheme = isDarkTheme
                    )
                }
            }

            MainScreenState.IncomeScreenForAdd -> {
                IncomeScreen(
                    viewModelFactory = viewModelFactory,
                    onBackHandlerClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    },
                    isDarkTheme = isDarkTheme,
                    paddingValues = paddingValues
                )
            }

            MainScreenState.ExpenseScreenForAdd -> {
                ExpenseScreen(
                    paddingValues = paddingValues,
                    viewModelFactory = viewModelFactory,
                    onBackHandlerClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    },
                    isDarkTheme = isDarkTheme
                )
            }

            is MainScreenState.ExpenseScreenForChange -> {
                ExpenseScreen(
                    paddingValues = paddingValues,
                    isAddMode = false,
                    expense = screenState.expense,
                    viewModelFactory = viewModelFactory,
                    onBackHandlerClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    },
                    isDarkTheme = isDarkTheme
                )
            }

            is MainScreenState.IncomeScreenForChange -> {
                IncomeScreen(
                    isAddMode = false,
                    income = screenState.income,
                    viewModelFactory = viewModelFactory,
                    onBackHandlerClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    },
                    isDarkTheme = isDarkTheme,
                    paddingValues = paddingValues
                )
            }

            MainScreenState.CurrencyScreen -> {
                CurrencyScreen(
                    viewModelFactory = viewModelFactory,
                    onBackHandlerClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    },
                    isDarkTheme = isDarkTheme
                )
            }

            is MainScreenState.BankRuDepositsScreen -> {
                WebViewScreen(
                    url = screenState.url,
                    onBackClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    }
                )
            }

            is MainScreenState.BankRuCreditsScreen -> {
                WebViewScreen(
                    url = screenState.url,
                    onBackClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    }
                )
            }

            is MainScreenState.BankRuMicroloansScreen -> {
                WebViewScreen(
                    url = screenState.url,
                    onBackClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    }
                )
            }

            is MainScreenState.BankRuMortgageScreen -> {
                WebViewScreen(
                    url = screenState.url,
                    onBackClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    }
                )
            }

            is MainScreenState.BankRuNewsScreen -> {
                WebViewScreen(
                    url = screenState.url,
                    onBackClickListener = {
                        state.value = MainScreenState.AnyScreenWithTopAndBottomNav
                    }
                )
            }
        }
    }
}

@Composable
private fun AnyScreenWithTopAndBottomNavContent(
    state: MutableState<MainScreenState>,
    navigationState: NavigationState,
    viewModelFactory: ViewModelFactory,
    isDarkTheme: Boolean,
    topBarState: MutableState<TopNavigationItem>,
    startDateState: MutableState<Date>,
    endDateState: MutableState<Date>,
    onCheckedChangeClickListener: (Boolean) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(10.dp),
                drawerContainerColor = MaterialTheme.colorScheme.background,
                drawerContentColor = MaterialTheme.colorScheme.onBackground,
                drawerTonalElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(16.dp))
                    Icon(
                        painter = painterResource(R.drawable.icon),
                        contentDescription = stringResource(R.string.ruble_icon),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    LazyColumn {
                        item {
                            MyTextButton(
                                text = stringResource(R.string.currency_value),
                                onTextButtonClickListener = {
                                    state.value = MainScreenState.CurrencyScreen
                                }
                            )
                        }
                        item {
                            MyTextButton(
                                text = stringResource(R.string.deposits),
                                onTextButtonClickListener = {
                                    state.value = MainScreenState.BankRuDepositsScreen()
                                }
                            )
                        }
                        item {
                            MyTextButton(
                                text = stringResource(R.string.credits),
                                onTextButtonClickListener = {
                                    state.value = MainScreenState.BankRuCreditsScreen()
                                }
                            )
                        }
                        item {
                            MyTextButton(
                                text = stringResource(R.string.microloans),
                                onTextButtonClickListener = {
                                    state.value = MainScreenState.BankRuMicroloansScreen()
                                }
                            )
                        }
                        item {
                            MyTextButton(
                                text = stringResource(R.string.mortgage),
                                onTextButtonClickListener = {
                                    state.value = MainScreenState.BankRuMortgageScreen()
                                }
                            )
                        }
                        item {
                            MyTextButton(
                                text = stringResource(R.string.news),
                                onTextButtonClickListener = {
                                    state.value = MainScreenState.BankRuNewsScreen()
                                }
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.dark_theme),
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(8.dp)
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Switch(
                            checked = isDarkTheme,
                            colors = SwitchDefaults.colors(
                                checkedBorderColor = MaterialTheme.colorScheme.onBackground,
                                uncheckedBorderColor = MaterialTheme.colorScheme.onBackground,
                                disabledCheckedBorderColor = MaterialTheme.colorScheme.onBackground,
                                disabledUncheckedBorderColor = MaterialTheme.colorScheme.onBackground,
                                checkedThumbColor = MaterialTheme.colorScheme.onBackground,
                                uncheckedThumbColor = MaterialTheme.colorScheme.onBackground,
                                disabledCheckedThumbColor = MaterialTheme.colorScheme.onBackground,
                                disabledUncheckedThumbColor = MaterialTheme.colorScheme.onBackground,
                                checkedIconColor = MaterialTheme.colorScheme.background,
                                uncheckedIconColor = MaterialTheme.colorScheme.onBackground,
                                disabledCheckedIconColor = MaterialTheme.colorScheme.onBackground,
                                disabledUncheckedIconColor = MaterialTheme.colorScheme.onBackground,
                                uncheckedTrackColor = MaterialTheme.colorScheme.background,
                                disabledUncheckedTrackColor = MaterialTheme.colorScheme.background,
                                disabledCheckedTrackColor = MaterialTheme.colorScheme.background,
                                checkedTrackColor = MaterialTheme.colorScheme.background
                            ),
                            thumbContent = {
                                if (isDarkTheme) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(SwitchDefaults.IconSize)
                                    )
                                }
                            },
                            onCheckedChange = { onCheckedChangeClickListener(it) }
                        )
                    }
                }
            }
            BackHandler {
                coroutineScope.launch { drawerState.close() }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            bottomBar = {
                MainBottomNavigation(navigationState = navigationState)
            },
            topBar = {
                MainTopBar(
                    state = topBarState,
                    onMenuClickListener = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
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
                        },
                        isDarkTheme = isDarkTheme
                    )
                },
                expensesScreenContent = {
                    ExpensesCheckScreen(
                        viewModelFactory = viewModelFactory,
                        startDate = startDateState.value,
                        endDate = endDateState.value,
                        paddingValues = paddingValues,
                        onExpenseClickListener = { expense ->
                            state.value =
                                MainScreenState.ExpenseScreenForChange(expense)
                        },
                        onFABClickListener = {
                            state.value = MainScreenState.ExpenseScreenForAdd
                        },
                        isDarkTheme = isDarkTheme
                    )
                },
                totalsScreenContent = {
                    TotalScreen(
                        viewModelFactory = viewModelFactory,
                        paddingValues = paddingValues,
                        startDate = startDateState.value,
                        endDate = endDateState.value,
                        isDarkTheme = isDarkTheme
                    )
                }
            )
        }
    }
}

@Composable
private fun MyTextButton(
    text: String,
    onTextButtonClickListener: () -> Unit
) {
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onTextButtonClickListener()
        }
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            textDecoration = TextDecoration.Underline,
            text = text,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}