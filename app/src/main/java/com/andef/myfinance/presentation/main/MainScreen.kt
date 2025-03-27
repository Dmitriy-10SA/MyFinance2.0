package com.andef.myfinance.presentation.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.navigation.main.Screen
import com.andef.myfinance.navigation.defaultScreenAnim
import com.andef.myfinance.navigation.main.MainNavGraph
import com.andef.myfinance.navigation.main.MainNavigationState
import com.andef.myfinance.navigation.main.rememberMainNavigationState
import com.andef.myfinance.navigation.rangePickerAnim
import com.andef.myfinance.presentation.expense.ExpensesScreen
import com.andef.myfinance.presentation.income.IncomesScreen
import com.andef.myfinance.presentation.picker.MyFinanceRangeDatePicker
import com.andef.myfinance.presentation.total.TotalsScreen
import com.andef.myfinance.presentation.webview.WebViewLink
import com.andef.myfinance.ui.theme.Blue
import com.andef.myfinance.ui.theme.Orange
import com.andef.myfinance.ui.theme.White
import com.andef.myfinance.utils.ui.getCurrentDestination
import com.andef.myfinance.utils.ui.toDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    isDarkTheme: Boolean,
    viewModelFactory: ViewModelFactory,
    onIncomeFABClickListener: () -> Unit,
    onExpenseFABClickListener: () -> Unit,
    onIncomeClickListener: (Income) -> Unit,
    onExpenseClickListener: (Expense) -> Unit,
    onCheckedChangeClickListener: (Boolean) -> Unit,
    onIncomeAnalysisClickListener: () -> Unit,
    onExpenseAnalysisClickListener: () -> Unit,
    onCurrencyValueClickListener: () -> Unit,
    onWebViewActionClickListener: (String) -> Unit,
    onReminderClickListener: () -> Unit,
    onAboutDeveloperClickListener: () -> Unit
) {
    val mainNavigationState = rememberMainNavigationState()

    val isRangeDatePickerScreen = remember { mutableStateOf(false) }
    val topBarState = remember { mutableStateOf(MainTopBarItem.Day as MainTopBarItem) }
    val startDate = remember { mutableStateOf(Date()) }
    val endDate = remember { mutableStateOf(Date()) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    SetDate(topBarState = topBarState, startDate = startDate, endDate = endDate)

    AnimatedContent(
        targetState = isRangeDatePickerScreen.value,
        transitionSpec = {
            if (isRangeDatePickerScreen.value) rangePickerAnim else defaultScreenAnim
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { isRangeDatePicker ->
        if (!isRangeDatePicker) {
            ModalNavigationDrawer(
                drawerContent = {
                    ModalDrawerSheet(
                        drawerShape = RoundedCornerShape(10.dp),
                        drawerContainerColor = MaterialTheme.colorScheme.background,
                        drawerContentColor = MaterialTheme.colorScheme.onBackground,
                        drawerTonalElevation = 8.dp
                    ) {
                        ModalDrawerSheetContent(
                            isDarkTheme = isDarkTheme,
                            onCheckedChangeClickListener = onCheckedChangeClickListener,
                            onCurrencyValueClickListener = onCurrencyValueClickListener,
                            onExpenseAnalysisClickListener = onExpenseAnalysisClickListener,
                            onIncomeAnalysisClickListener = onIncomeAnalysisClickListener,
                            onWebViewActionClickListener = onWebViewActionClickListener,
                            onReminderClickListener = onReminderClickListener,
                            onAboutDeveloperClickListener = onAboutDeveloperClickListener
                        )
                    }
                    BackHandler {
                        coroutineScope.launch { drawerState.close() }
                    }
                },
                drawerState = drawerState
            ) {
                MainScreenContent(
                    topBarState = topBarState,
                    isRangeDatePickerScreen = isRangeDatePickerScreen,
                    mainNavigationState = mainNavigationState,
                    isDarkTheme = isDarkTheme,
                    onIncomeFABClickListener = onIncomeFABClickListener,
                    onExpenseFABClickListener = onExpenseFABClickListener,
                    onExpenseClickListener = onExpenseClickListener,
                    onIncomeClickListener = onIncomeClickListener,
                    startDate = startDate,
                    endDate = endDate,
                    viewModelFactory = viewModelFactory,
                    drawerState = drawerState,
                    coroutineScope = coroutineScope
                )
            }
        } else {
            MyFinanceRangeDatePicker(
                onCloseClickListener = {
                    isRangeDatePickerScreen.value = false
                },
                onSaveClickListener = { start, end ->
                    startDate.value = Date(start)
                    endDate.value = Date(end)
                    isRangeDatePickerScreen.value = false
                },
                isDarkTheme = isDarkTheme
            )
        }
    }
}

@Composable
private fun ModalDrawerSheetContent(
    isDarkTheme: Boolean,
    onCheckedChangeClickListener: (Boolean) -> Unit,
    onIncomeAnalysisClickListener: () -> Unit,
    onExpenseAnalysisClickListener: () -> Unit,
    onCurrencyValueClickListener: () -> Unit,
    onWebViewActionClickListener: (String) -> Unit,
    onReminderClickListener: () -> Unit,
    onAboutDeveloperClickListener: () -> Unit
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
        Spacer(modifier = Modifier.padding(12.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                MyTextButton(
                    text = stringResource(R.string.income_analysis),
                    onTextButtonClickListener = onIncomeAnalysisClickListener
                )
            }
            item {
                MyTextButton(
                    text = stringResource(R.string.expenses_analysis),
                    onTextButtonClickListener = onExpenseAnalysisClickListener
                )
            }
            item {
                MyTextButton(
                    text = stringResource(R.string.currency_value),
                    onTextButtonClickListener = onCurrencyValueClickListener
                )
            }
            item {
                MyTextButton(
                    text = stringResource(R.string.reminder),
                    onTextButtonClickListener = onReminderClickListener
                )
            }
            item {
                MyTextButton(
                    text = stringResource(R.string.deposits),
                    onTextButtonClickListener = {
                        onWebViewActionClickListener(WebViewLink.BankRuDepositsScreen.link)
                    }
                )
            }
            item {
                MyTextButton(
                    text = stringResource(R.string.credits),
                    onTextButtonClickListener = {
                        onWebViewActionClickListener(WebViewLink.BankRuCreditsScreen.link)
                    }
                )
            }
            item {
                MyTextButton(
                    text = stringResource(R.string.microloans),
                    onTextButtonClickListener = {
                        onWebViewActionClickListener(WebViewLink.BankRuMicroloansScreen.link)
                    }
                )
            }
            item {
                MyTextButton(
                    text = stringResource(R.string.mortgage),
                    onTextButtonClickListener = {
                        onWebViewActionClickListener(WebViewLink.BankRuMortgageScreen.link)
                    }
                )
            }
            item {
                MyTextButton(
                    text = stringResource(R.string.news),
                    onTextButtonClickListener = {
                        onWebViewActionClickListener(WebViewLink.BankRuNewsScreen.link)
                    }
                )
            }
            item {
                MyTextButton(
                    text = stringResource(R.string.about_developer),
                    onTextButtonClickListener = {
                        onAboutDeveloperClickListener()
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
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

@Composable
private fun MyTextButton(
    text: String,
    onTextButtonClickListener: () -> Unit
) {
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        onClick = onTextButtonClickListener
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            textDecoration = TextDecoration.Underline,
            text = text,
            textAlign = TextAlign.Start,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun MainScreenContent(
    topBarState: MutableState<MainTopBarItem>,
    isRangeDatePickerScreen: MutableState<Boolean>,
    mainNavigationState: MainNavigationState,
    isDarkTheme: Boolean,
    onIncomeFABClickListener: () -> Unit,
    onExpenseFABClickListener: () -> Unit,
    viewModelFactory: ViewModelFactory,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    startDate: MutableState<Date>,
    endDate: MutableState<Date>,
    onIncomeClickListener: (Income) -> Unit,
    onExpenseClickListener: (Expense) -> Unit
) {
    Scaffold(
        topBar = {
            MainTopBar(
                topBarState = topBarState,
                onPeriodItemClickListener = {
                    isRangeDatePickerScreen.value = true
                },
                onMenuClickListener = {
                    coroutineScope.launch { drawerState.open() }
                }
            )
        },
        bottomBar = {
            MainBottomBar(mainNavigationState = mainNavigationState)
        },
        floatingActionButton = {
            MainFAB(
                mainNavigationState = mainNavigationState,
                isDarkTheme = isDarkTheme,
                onIncomeFABClickListener = onIncomeFABClickListener,
                onExpenseFABClickListener = onExpenseFABClickListener
            )
        }
    ) { paddingValues ->
        MainNavGraph(
            navHostController = mainNavigationState.navHostController,
            incomesScreenContent = {
                IncomesScreen(
                    paddingValues = paddingValues,
                    viewModelFactory = viewModelFactory,
                    startDate = startDate.value,
                    endDate = endDate.value,
                    onIncomeClickListener = onIncomeClickListener
                )
            },
            expensesScreenContent = {
                ExpensesScreen(
                    paddingValues = paddingValues,
                    viewModelFactory = viewModelFactory,
                    startDate = startDate.value,
                    endDate = endDate.value,
                    onExpenseClickListener = onExpenseClickListener
                )
            },
            totalsScreenContent = {
                TotalsScreen(
                    paddingValues = paddingValues,
                    viewModelFactory = viewModelFactory,
                    isDarkTheme = isDarkTheme,
                    startDate = startDate.value,
                    endDate = endDate.value
                )
            }
        )
    }
}

@Composable
private fun SetDate(
    topBarState: MutableState<MainTopBarItem>,
    startDate: MutableState<Date>,
    endDate: MutableState<Date>
) {
    LaunchedEffect(topBarState.value) {
        when (topBarState.value) {
            MainTopBarItem.Day -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().toDate()
            }

            MainTopBarItem.Week -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().minusWeeks(1).toDate()
            }

            MainTopBarItem.Month -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().minusMonths(1).toDate()
            }

            MainTopBarItem.Year -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().minusYears(1).toDate()
            }

            MainTopBarItem.Period -> {}
        }
    }
}

@Composable
private fun MainFAB(
    isDarkTheme: Boolean,
    mainNavigationState: MainNavigationState,
    onIncomeFABClickListener: () -> Unit,
    onExpenseFABClickListener: () -> Unit
) {
    val currentDestination = getCurrentDestination(mainNavigationState)

    AnimatedVisibility(
        visible = currentDestination?.route in listOf(
            Screen.MainScreen.Incomes.route,
            Screen.MainScreen.Expenses.route
        ),
        enter = scaleIn(tween(800)),
        exit = scaleOut(tween(800))
    ) {
        if (currentDestination?.route == Screen.MainScreen.Incomes.route) {
            FAB(isDarkTheme = isDarkTheme, onFABClickListener = onIncomeFABClickListener)
        } else if (currentDestination?.route == Screen.MainScreen.Expenses.route) {
            FAB(isDarkTheme = isDarkTheme, onFABClickListener = onExpenseFABClickListener)
        }
    }
}

@Composable
private fun FAB(isDarkTheme: Boolean, onFABClickListener: () -> Unit) {
    FloatingActionButton(
        containerColor = if (isDarkTheme) Blue else Orange,
        contentColor = White,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        onClick = onFABClickListener
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = White,
            contentDescription = stringResource(R.string.button_for_add)
        )
    }
}

@Composable
private fun MainTopBar(
    topBarState: MutableState<MainTopBarItem>,
    onPeriodItemClickListener: () -> Unit,
    onMenuClickListener: () -> Unit
) {
    Column {
        TitleAndMenuIconButtonOfTopBar(onMenuClickListener = onMenuClickListener)
        DateTabsOfTopBar(
            topBarState = topBarState,
            onPeriodItemClickListener = onPeriodItemClickListener
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TitleAndMenuIconButtonOfTopBar(onMenuClickListener: () -> Unit) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = { Text(text = stringResource(R.string.app_name), fontSize = 24.sp) },
        navigationIcon = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                onClick = onMenuClickListener
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.menu)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateTabsOfTopBar(
    topBarState: MutableState<MainTopBarItem>,
    onPeriodItemClickListener: () -> Unit
) {
    PrimaryScrollableTabRow(
        edgePadding = 0.dp,
        selectedTabIndex = topBarState.value.index,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        indicator = {
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(topBarState.value.index)
                    .height(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(horizontal = 28.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    ) {
        for (item in mainTopBarItems) {
            Tab(
                selectedContentColor = MaterialTheme.colorScheme.onBackground,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                selected = topBarState.value == item,
                onClick = {
                    topBarState.value = item
                    if (item == MainTopBarItem.Period) {
                        onPeriodItemClickListener()
                    }
                },
                text = { Text(text = stringResource(item.titleResId)) }
            )
        }
    }
}

@Composable
private fun MainBottomBar(mainNavigationState: MainNavigationState) {
    NavigationBar(
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                ambientColor = MaterialTheme.colorScheme.onBackground
            ),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        for (item in mainBottomBarItems) {
            NavigationBarItem(
                selected = getCurrentDestination(mainNavigationState)?.route == item.route,
                onClick = { mainNavigationState.navigateTo(item.route) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(item.iconResId),
                        contentDescription = stringResource(item.contentDescriptionResId)
                    )
                },
                label = { Text(text = stringResource(item.titleResId), fontSize = 13.sp) },
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.onBackground,
                    selectedTextColor = MaterialTheme.colorScheme.onBackground,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    disabledTextColor = MaterialTheme.colorScheme.onBackground,
                    disabledIconColor = MaterialTheme.colorScheme.onBackground,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground
                ),
                alwaysShowLabel = false
            )
        }
    }
}

private val mainTopBarItems = listOf(
    MainTopBarItem.Day,
    MainTopBarItem.Week,
    MainTopBarItem.Month,
    MainTopBarItem.Year,
    MainTopBarItem.Period
)

private val mainBottomBarItems = listOf(
    MainBottomBarItem.Incomes,
    MainBottomBarItem.Expenses,
    MainBottomBarItem.Totals
)