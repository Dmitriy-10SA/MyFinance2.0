package com.andef.myfinance.presentation.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.navigation.Screen
import com.andef.myfinance.navigation.defaultScreenAnim
import com.andef.myfinance.navigation.main.MainNavGraph
import com.andef.myfinance.navigation.main.MainNavigationState
import com.andef.myfinance.navigation.main.rememberMainNavigationState
import com.andef.myfinance.navigation.rangePickerAnim
import com.andef.myfinance.presentation.datepicker.MyFinanceRangeDatePicker
import com.andef.myfinance.presentation.expense.ExpensesScreen
import com.andef.myfinance.presentation.income.IncomesScreen
import com.andef.myfinance.presentation.total.TotalsScreen
import com.andef.myfinance.ui.theme.Blue
import com.andef.myfinance.ui.theme.Orange
import com.andef.myfinance.ui.theme.White
import com.andef.myfinance.utils.ui.toDate
import com.andef.myfinance.utils.ui.getCurrentDestination
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
    onExpenseClickListener: (Expense) -> Unit
) {
    val mainNavigationState = rememberMainNavigationState()

    val isRangeDatePickerScreen = remember { mutableStateOf(false) }
    val topBarState = remember { mutableStateOf(MainTopBarItem.Day as MainTopBarItem) }
    val startDate = remember { mutableStateOf(Date()) }
    val endDate = remember { mutableStateOf(Date()) }

    SetDate(topBarState = topBarState, startDate = startDate, endDate = endDate)

    AnimatedContent(
        targetState = isRangeDatePickerScreen.value,
        transitionSpec = {
            if (isRangeDatePickerScreen.value) rangePickerAnim else defaultScreenAnim
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { isRangeDatePicker ->
        if (!isRangeDatePicker) {
            Scaffold(
                topBar = {
                    MainTopBar(
                        topBarState = topBarState,
                        onPeriodItemClickListener = {
                            isRangeDatePickerScreen.value = true
                        },
                        onMenuClickListener = {
                            // TODO("Меню открыть")
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
                label = { Text(text = stringResource(item.titleResId)) },
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